package com.unitrust.timestamp3A.service.file.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unitrust.timestamp3A.dao.file.FileDao;
import com.unitrust.timestamp3A.model.file.Files;
import com.unitrust.timestamp3A.service.file.FileService;

@Service(value = "fileService")
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao fileDao;

	
	@Override
	public List<Files> getCustomById(Integer id) {
		// TODO Auto-generated method stub
		return fileDao.getCustomById(id);
	}


	@Override
	public int uploadFiles(Files files) {
		// TODO Auto-generated method stub
		
		return fileDao.uploadFiles(files);
	}


	@Override
	public Files findPhotoFile(Integer relationId, Integer fileType) {
		// TODO Auto-generated method stub
		return fileDao.findPhotoFile(relationId, fileType);
	}


	@Override
	public Files findPhotoFile(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("sql....................");
		return fileDao.findPhotoFileById(id);
	}

}
