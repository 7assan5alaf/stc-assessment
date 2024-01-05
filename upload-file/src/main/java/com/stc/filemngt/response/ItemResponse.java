package com.stc.filemngt.response;

public class ItemResponse {

	private byte []file;
	private String path;
	private String group_name;
	
	
	public ItemResponse(byte[] file, String path, String group_name) {
		super();
		this.file = file;
		this.path = path;
		this.group_name = group_name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFile_path() {
		return path;
	}
	public void setFile_path(String path) {
		this.path = path;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}
