package com.stc.filemngt.service;

import org.springframework.stereotype.Service;

import com.stc.filemngt.entity.PermissionGroup;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.PermissionGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionGroupService {

	
	private final PermissionGroupRepository groupRepository;
	public PermissionGroup findPermissionGroupById(Long id) {
		return groupRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("Permission Group Not Found"));
	}
	
	public PermissionGroup insert(PermissionGroup group) {
		return groupRepository.save(group);
	}

}
