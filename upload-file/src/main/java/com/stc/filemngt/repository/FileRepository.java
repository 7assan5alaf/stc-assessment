package com.stc.filemngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stc.filemngt.entity.FileStorage;
@Repository
public interface FileRepository extends JpaRepository<FileStorage, Long> {

	@Query("SELECT f FROM Files f WHERE f.id=:fileId")
	public FileStorage findFileById(@Param("fileId") Long id);
}
