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
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
public class Permissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String user_email;
	private String permission_level;
	
	@ManyToOne
	@JoinColumn(columnDefinition = "group_id",referencedColumnName = "id")
	private PermissionGroup permissionGroup;
	
	
}
