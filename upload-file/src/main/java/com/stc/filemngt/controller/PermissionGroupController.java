package com.stc.filemngt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.filemngt.entity.PermissionGroup;
import com.stc.filemngt.service.PermissionGroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api-group")
@RequiredArgsConstructor
public class PermissionGroupController {

	private final PermissionGroupService groupService;
	
	@PostMapping("/insert")
	public ResponseEntity<?>insert(@RequestBody PermissionGroup group){
		return ResponseEntity.ok(groupService.insert(group));
	}
}
