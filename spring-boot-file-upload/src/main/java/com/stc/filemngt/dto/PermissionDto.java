package com.stc.filemngt.dto;

public class PermissionDto {
	
	private String user_email;
	private String permission_level;
	private Long groupId;
	
	
	
	public PermissionDto() {
		super();
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getPermission_level() {
		return permission_level;
	}
	public void setPermission_level(String permission_level) {
		this.permission_level = permission_level;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	

}
