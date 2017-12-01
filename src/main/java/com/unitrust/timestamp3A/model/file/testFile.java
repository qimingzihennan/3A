package com.unitrust.timestamp3A.model.file;

import org.springframework.web.multipart.MultipartFile;

public class testFile extends Files{
	
	private  MultipartFile  pictureFile;

	public MultipartFile getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(MultipartFile pictureFile) {
		this.pictureFile = pictureFile;
	}
	
	

}
