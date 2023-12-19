package com.stc.filemngt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stc.filemngt.service.FileService;
import com.stc.filemngt.service.ItemService;
@RestController
@RequestMapping("/api-access")
public class UserAccessController {

	@Autowired
	private FileService fileService;	
	@Autowired
	private ItemService itemService;

	@PostMapping("/add-item/{permissionId}")
	public ResponseEntity<?> createItem(@RequestParam String itemName,@RequestParam Long groupId
			,@RequestParam String parentItemId,@PathVariable Long permissionId) {
		return itemService.createItem(itemName,groupId,parentItemId, permissionId);
	}
	
	@PostMapping("/add-file/{permissionId}")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file,
			@RequestParam Long parentItemId,@PathVariable Long permissionId ) {
		return fileService.uploadFile(file,parentItemId,permissionId);
	}
	
	@GetMapping("/view/{fileId}/{PermissionId}")
	 public ResponseEntity<?> getById(@PathVariable Long fileId,@PathVariable Long PermissionId ) {
		return fileService.getFileById(fileId,PermissionId);
	}

	
	@GetMapping("/download/{fileId}/{permissionId}")
	public ResponseEntity<?>downloadFile(@PathVariable Long fileId,@PathVariable Long permissionId ){
		return fileService.downloadFile(fileId, permissionId);
	}
	
	
}
