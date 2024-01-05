package com.stc.filemngt.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "file_storage")
public class FileStorage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   
	private String fileFormat;
	@Transient
    @Value("${file.upload-dir}")
	private String uploadDir;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "item_id", referencedColumnName = "id")
	private Item item;

	public FileStorage() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String binary) {
		this.fileFormat = binary;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
