package com.stc.filemngt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="files")
public class FileStorage {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private byte []file;
    
    @ManyToOne
    @JoinColumn(columnDefinition = "item_id",referencedColumnName = "id")
    private Item item;
    
    
	public FileStorage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
    
}
