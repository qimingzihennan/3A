package com.unitrust.timestamp3A.controller.file;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unitrust.timestamp3A.model.file.Files;
import com.unitrust.timestamp3A.service.file.FileService;



@Controller
public class PhotoFileController {
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 照片预览
	 */
	@RequestMapping(value = "/photofile/download/{id}", method=RequestMethod.GET)
	public void save(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		OutputStream outputStream = null;
		try {
			Files photoFile = fileService.findPhotoFile(id);
			byte[] data = photoFile.getFile();  
			System.out.println(data.toString());
	        String fileName = photoFile.getFileName();  
	        fileName = URLEncoder.encode(fileName, "UTF-8");  
	        response.reset();  
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	        response.addHeader("Content-Length", "" + data.length);  
	        response.setContentType("application/octet-stream;charset=UTF-8");  
	        outputStream = new BufferedOutputStream(response.getOutputStream());  
	        outputStream.write(data);  
		} catch (Exception e) {
			// 判断suffix
			// 图片请求可以在此显示一个默认图片
			// file显示文件已损坏等错误提示...
			System.out.println("读取文件失败");
		} finally {
			if( outputStream != null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
				}

			}
		}
	}
	
}
