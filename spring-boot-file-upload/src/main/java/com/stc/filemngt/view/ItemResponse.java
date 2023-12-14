package com.stc.filemngt.view;

public class ItemResponse {

	private byte []file;
	private String file_name;
	private String group_name;
	
	
	public ItemResponse(byte[] file, String file_name, String group_name) {
		super();
		this.file = file;
		this.file_name = file_name;
		this.group_name = group_name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}
