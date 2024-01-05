package com.stc.filemngt.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.stc.filemngt.entity.PermissionGroup;
import com.stc.filemngt.repository.PermissionGroupRepository;
@Component
public class AppCommendLinner implements CommandLineRunner {
    @Autowired
	private PermissionGroupRepository groupRepository;

	@Override
	public void run(String... args) throws Exception {
		PermissionGroup group =new PermissionGroup();
		group.setId(1L);
		group.setName("admin_group");
		 groupRepository.save(group);
		
	}

}
