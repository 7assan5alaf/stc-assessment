package com.stc.filemngt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="file_storage")
@Getter
@Setter
@NoArgsConstructor
public class FileStorage {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private byte []file;
    
    @ManyToOne
    @JoinColumn(columnDefinition = "item_id",referencedColumnName = "id")
    private Item item;
   
}
