package com.stc.filemngt.response;

public class ItemResponse {

	private  String fileName;
	private String path;
	private String group_name;
	
	
	public ItemResponse(String fileName, String path, String group_name) {
		super();
		this.fileName = fileName;
		this.path = path;
		this.group_name = group_name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String file) {
		this.fileName = file;
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
