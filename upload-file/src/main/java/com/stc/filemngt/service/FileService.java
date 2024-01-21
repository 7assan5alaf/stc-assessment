package com.stc.filemngt.service;

import java.io.IOException;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stc.filemngt.entity.FileStorage;
import com.stc.filemngt.entity.Item;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.FileStorageException;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.FileRepository;
import com.stc.filemngt.response.ItemResponse;
import com.stc.filemngt.utils.FileStorageUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {

	private final FileRepository fileRepository;

	private final ItemService itemService;

	private final PermissionService permissionService;

	public FileStorage findById(Long id) {
		return fileRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("File Not Found"));
	}

	public String uploadFile(MultipartFile file, Long parantItemId, Long permissionId) {
		Permissions permission = permissionService.findPermissionById(permissionId);
		Item parentItem = itemService.findItemById(parantItemId);
		if (parentItem.getType().equals("File"))
			return "Can't upload file because invalid itemId";
		if (permission.getPermission_level().equals("EDIT")
				&& parentItem.getPermissionGroup().getName().equals(permission.getPermissionGroup().getName())) {

			try {
				FileStorage fileStorage = new FileStorage();
				Item item = new Item();
				// Create Item
				item.setName(file.getOriginalFilename());
				item.setPath(parentItem.getPath() + "\\" + parentItem.getName());
				item.setPermissionGroup(parentItem.getPermissionGroup());
				item.setType("File");
				itemService.insert(item);
				// upload file
				fileStorage.setFile(FileStorageUtil.compressFile(file.getBytes()));
				fileStorage.setItem(item);
				fileRepository.save(fileStorage);
				return "Add file successfully";
			} catch (IOException e) {
				throw new FileStorageException(
						"Could not store file " + file.getOriginalFilename() + ". Please try again!", e);
			}

		}
		return "Not Allwoed";

	}

	public ResponseEntity<?> viewFile(Long fileId, Long permissionId) {

		Permissions permission = permissionService.findPermissionById(permissionId);
		FileStorage fileStorage = findById(fileId);
		if (Objects.isNull(fileStorage))
			return ResponseEntity.ok("File not found");
		ItemResponse item = new ItemResponse(fileStorage.getFile(), fileStorage.getItem().getPath(),
				fileStorage.getItem().getName());
		if (fileStorage.getItem().getPermissionGroup().getName().equals(permission.getPermissionGroup().getName())) {
			if (permission.getPermission_level().equals("EDIT")) {
				return ResponseEntity.ok(item);
			}
		}
		return ResponseEntity.ok("Not Allowed");

	}

	public ResponseEntity<?> downloadFile(Long fileId, Long permissionId) {
		Permissions permission = permissionService.findPermissionById(permissionId);
		FileStorage file = findById(fileId);
		if (file.getItem().getPermissionGroup().getName().equals(permission.getPermissionGroup().getName())) {
			if (permission.getPermission_level().equals("EDIT"))
				return ResponseEntity.ok(FileStorageUtil.dcompressFile(findById(fileId).getFile()));
		}

		return ResponseEntity.ok("Not Allowed");
	}

}
