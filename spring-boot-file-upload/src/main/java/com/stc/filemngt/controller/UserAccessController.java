package com.stc.filemngt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stc.filemngt.error.FileStorageException;
import com.stc.filemngt.service.FileService;
import com.stc.filemngt.service.ItemService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api-access")
public class UserAccessController {

	@Autowired(required = false)
	private FileService fileService;
	@Autowired
	private ItemService itemService;

	@PostMapping("/add-item/{permissionId}")
	public ResponseEntity<?> createItem(@RequestParam String itemName, @RequestParam Long groupId,
			@RequestParam String parentItemId, @PathVariable Long permissionId) {
		return itemService.createItem(itemName, groupId, parentItemId, permissionId);
	}

	@PostMapping("/add-file/{permissionId}")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @RequestParam Long itemId,
			@PathVariable Long permissionId) {
		return fileService.uploadFile(file, itemId, permissionId);
	}

	@GetMapping("/downloadFile/{fileId}/{permissionId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId, @PathVariable Long permissionId,
			HttpServletRequest request) {
		Resource resource = null;
		try {
			resource = (Resource) fileService.loadFileAsResource(fileId, permissionId);
		} catch (Exception e1) {
			throw new FileStorageException("Invalid fileId or permissionId");
		}
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			throw new FileStorageException("Invalid fileId or permissionId");
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);

	}

	@GetMapping("/view/{fileId}/{PermissionId}")
	public ResponseEntity<?> getById(@PathVariable Long fileId, @PathVariable Long PermissionId) {
		return fileService.getFileById(fileId, PermissionId);
	}

}
