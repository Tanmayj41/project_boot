package com.app.pojos;
import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class temp {
	private Integer id;
	private MultipartFile file;
	public temp() {
		// TODO Auto-generated constructor stub
	}
	public temp(MultipartFile file) {
		super();
		this.file = file;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Lob
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "temp [file=" + file + "]";
	}
	
}
