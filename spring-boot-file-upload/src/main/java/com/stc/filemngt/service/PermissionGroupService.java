package com.stc.filemngt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.filemngt.entity.PermissionGroup;
import com.stc.filemngt.error.RecordNotFoundException;
import com.stc.filemngt.repository.PermissionGroupRepository;

@Service
public class PermissionGroupService {

	@Autowired
	private PermissionGroupRepository groupRepository;
	public PermissionGroup findPermissionGroupById(Long id) {
		return groupRepository.findById(id)
				.orElseThrow(()->new RecordNotFoundException("Permission Group Not Found"));
	}

}
