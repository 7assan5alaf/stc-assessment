package com.stc.filemngt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.filemngt.entity.Permissions;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
}
