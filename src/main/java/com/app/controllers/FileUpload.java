package com.app.controllers;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String toString() {
		if(file==null)
			return null;
		return String.format("[MultipartFile => Name: %s, Type: %s, Size: %d KB]", file.getOriginalFilename(), file.getContentType(), file.getSize()/1024);
	}
}
