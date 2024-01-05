package com.stc.filemngt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stc.filemngt.entity.FileStorage;
import com.stc.filemngt.entity.Item;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.FileStorageException;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.FileRepository;
import com.stc.filemngt.response.ItemResponse;

import jakarta.annotation.PostConstruct;

@Service
public class FileService {

	private final Path fileStorageLocation;

	Logger logger = LoggerFactory.getLogger(FileService.class);
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PermissionGroupService groupService;

	private FileStorage fileStorage = new FileStorage();

	@Autowired
	public FileService(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
		this.fileStorageLocation = Paths.get(fileStorage.getUploadDir()).toAbsolutePath().normalize();

	}

	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public FileStorage findById(Long id) {
		return fileRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("File Not Found"));
	}

	// upload file
	public ResponseEntity<?> uploadFile(MultipartFile mfile, Long parantItemId, Long permissionId) {
		Permissions permission = permissionService.findPermissionById(permissionId);
		
		String parentItemName = itemService.findItemById(parantItemId).getName();
		if (itemService.findItemById(parantItemId).getType().equals("File"))
			return new ResponseEntity<Object>("Can't upload file because invalid itemId", HttpStatus.CONFLICT);
		String fileName = StringUtils.cleanPath(mfile.getOriginalFilename());
		
		
		if (permission.getPermission_level().equals("EDIT")) {
			try {
				// create Item
				Item item = new Item();
				item.setName(parentItemName + "\\" + fileName);
				item.setType("File");
				item.setPermissionGroup(groupService.findPermissionGroupById(1L));
				// create file
				FileStorage file = new FileStorage();

				Path targetLocation = this.fileStorageLocation.resolve(fileName);
				Files.copy(mfile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				file.setFileFormat(fileName);
				file.setItem(item);
				fileRepository.save(file);
			}
			

			catch (IOException e) {
				throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
			}

			return ResponseEntity.ok(fileName);
		}

		return new ResponseEntity<Object>("Not Allowed", HttpStatus.FORBIDDEN);
	}

	// download file
	public Object loadFileAsResource(Long fileId, Long permissionId) throws Exception {
		String permissinName = permissionService.findPermissionById(permissionId).getPermission_level();
		if (permissinName.equals("EDIT")) {
			FileStorage fileStorage = findById(fileId);
			Path filePath = this.fileStorageLocation.resolve(fileStorage.getFileFormat()).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;

			}
			//logger.error("file not found fileId--> " + fileId);
			return "resource not found";
		}
		//logger.error("permission not found id -->" + permissionId);
		return "invalid permission";
	}

	// view file
	public ResponseEntity<?> getFileById(Long id, Long permissionId) {
		Permissions permission = permissionService.findPermissionById(permissionId);
		ItemResponse response;
		if (permission.getPermission_level().equals("EDIT")) {
			try {
				FileStorage files = fileRepository.findFileById(id);
				if (Objects.isNull(files))
					return new ResponseEntity<Object>("File not found", HttpStatus.NOT_FOUND);

				//Path filePath = this.fileStorageLocation.resolve(files.getFileFormat()).normalize();
				response = new ItemResponse(files.getFileFormat(),
						 files.getItem().getName(),
						permission.getPermissionGroup().getName());
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			} catch (RuntimeException e) {
				throw new RecordNotFoundException("File Not found" + e.getMessage());
			}
		}
		return new ResponseEntity<Object>("Not Allowed", HttpStatus.FORBIDDEN);
	}

}
