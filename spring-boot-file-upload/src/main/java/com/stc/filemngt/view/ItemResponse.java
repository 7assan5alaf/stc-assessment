package com.stc.filemngt.view;

public class ItemResponse {

	private byte []file;
	private String file_path;
	private String group_name;
	
	public ItemResponse(byte[] file, String file_path, String group_name) {
		super();
		this.file = file;
		this.file_path = file_path;
		this.group_name = group_name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}
