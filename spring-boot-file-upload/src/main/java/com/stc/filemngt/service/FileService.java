package com.stc.filemngt.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stc.filemngt.entity.Files;
import com.stc.filemngt.entity.Item;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.FileRepository;
import com.stc.filemngt.utils.FileStorageUtil;
import com.stc.filemngt.view.ItemResponse;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private ItemService itemService;
	@Autowired 
	private PermissionService permissionService;
	@Autowired
	private PermissionGroupService groupService;
	
	
	
	public ResponseEntity<?> uploadFile(MultipartFile mfile,Long parentId,Long permissionId) {
		Permissions user=permissionService.findPermissionById(permissionId);
		String parentItemName=itemService.findItemById(parentId).getName();
		if(user.getPermission_level().equals("EDIT")) {
		String fileName=mfile.getOriginalFilename();
		Files file=new Files();
		Item item=new Item();
		try {
			//create item
			item.setName(parentItemName+"/"+fileName);
			item.setType("File");
			item.setPermissionGroup(groupService.findPermissionGroupById(1L));
			itemService.insert(item);
			//create file
			file.setFile(FileStorageUtil.compressFile(mfile.getBytes()));
			file.setItem(itemService.findItemById(item.getId()));
			fileRepository.save(file);
			return new ResponseEntity<Object>("File Added Successfully",HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		return new ResponseEntity<Object>("Not Allowed",HttpStatus.FORBIDDEN);
		
	}
	
	public ResponseEntity<?> getFileById(Long id,Long permissionId) {
	Permissions user=permissionService.findPermissionById(permissionId);
	ItemResponse response;
	  if(user.getPermission_level().equals("EDIT")) {
	  try {
		  Files files=fileRepository.findFileById(id);
		  response=new ItemResponse(files.getFile(), files.getItem().getName(),user.getPermissionGroup().getName());
		return new ResponseEntity<Object>( response,HttpStatus.OK);
	} catch (RuntimeException e) {
		throw new RecordNotFoundException("File Not found"+e.getMessage());
	}
	}
	  return new ResponseEntity<Object>("Not Allowed",HttpStatus.FORBIDDEN);
	}
	
	public Files findById(Long id) {
	return fileRepository.findById(id)
			.orElseThrow(()->new RecordNotFoundException("File Not Found"));
	}
	
	public ResponseEntity<?>downloadFile(Long fileId,Long permissionId){
		Permissions user=permissionService.findPermissionById(permissionId);
		if(user.getPermission_level().equals("EDIT")) {
			return ResponseEntity.ok( FileStorageUtil.dcompressFile(findById(fileId).getFile()));
		}
		
		return ResponseEntity.ok("You Are Blocked");
	}

}
