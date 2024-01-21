package com.stc.filemngt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionDto {
	
	private String user_email;
	private String permission_level;
	private Long groupId;

	

}
