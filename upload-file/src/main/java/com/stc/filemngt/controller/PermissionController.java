package com.stc.filemngt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.filemngt.dto.PermissionDto;
import com.stc.filemngt.entity.Permissions;
import com.stc.filemngt.service.PermissionService;

@RestController
@RequestMapping("/api-permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping("/insert")
	public String AddPermission(@RequestBody PermissionDto permission ) {
		return permissionService.addPermission(permission);
	}
	
	
	@GetMapping("/{id}")
	public Permissions getPermissionById(@PathVariable Long id) {
		return permissionService.findPermissionById(id);
	}
}
