package com.stc.filemngt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.filemngt.dto.PermissionDto;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.PermissionRepository;

@Service
public class PermissionService {
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private PermissionGroupService groupService;
	
	
	public String addPermission(PermissionDto dtoPermission) {
		 Permissions permission=new Permissions();
		 permission.setPermission_level(dtoPermission.getPermission_level());
		 permission.setPermissionGroup(
				 groupService.findPermissionGroupById(dtoPermission.getGroupId())
		 );
		 permission.setUser_email(dtoPermission.getUser_email());
		 permissionRepository.save(permission);
		 return "Permission Added Successfully";
		 
	}
	
	public Permissions findPermissionById(Long id) {
		return permissionRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("Permission Not Found"));
	}

}
