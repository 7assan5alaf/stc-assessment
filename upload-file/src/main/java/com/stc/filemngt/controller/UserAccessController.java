package com.stc.filemngt.controller;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-access")
@RequiredArgsConstructor
public class UserAccessController {

	private final FileService fileService;

	private final ItemService itemService;

	@PostMapping("/add-space")
	public ResponseEntity<?> createSpace(@RequestParam String itemName, @RequestParam Long groupId) {

		return ResponseEntity.ok(itemService.createSpace(itemName, groupId));
	}

	@PostMapping("/add-folder/{permissionId}")
	public ResponseEntity<?> createFolder(@RequestParam String itemName, @RequestParam Long parentItemId,
			@PathVariable Long permissionId) {
		return ResponseEntity.ok(itemService.createFolder(itemName, parentItemId, permissionId));
	}
    @PostMapping("/add-file/{permissionId}")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @RequestParam Long parentItemId,
			@PathVariable Long permissionId) {

		return ResponseEntity.ok(fileService.uploadFile(file, parentItemId, permissionId));
	}
    
    @GetMapping("/view-file/{fileId}/{permissionId}")
    public ResponseEntity<?>viewFile(@PathVariable Long fileId,@PathVariable Long permissionId){
    	return fileService.viewFile(fileId, permissionId);
    }
    @GetMapping("/download-file/{fileId}/{permissionId}")
    public ResponseEntity<?>downloadFile(@PathVariable Long fileId,@PathVariable Long permissionId){
    	return fileService.downloadFile(fileId, permissionId);
    }
    
//    @GetMapping("/{itemId}/{permissionId}")
//    public ResponseEntity<?>UCanAccessItemOrNot(@PathVariable Long itemId,@PathVariable Long permissionId){
//    	return ResponseEntity.ok(itemService.UCanAccessItemOrNot(itemId, permissionId));
//    }
    
}
