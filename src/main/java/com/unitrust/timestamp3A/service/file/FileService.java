package com.unitrust.timestamp3A.service.file;

import java.util.List;

import com.unitrust.timestamp3A.model.file.Files;

/**
 * 认证文件
 * @author tsa02
 *
 */
public interface FileService {
	/**
	 * 根据用户ID查询认证文件
	 * @param id
	 * @return
	 */
	public List<Files> getCustomById(Integer id);
	/**
	 * 用户认证上传照片
	 */ 
	public int uploadFiles(Files files);
	/**
	 * 根据用户ID和文件类型查询认证文件
	 * @param relationId
	 * @param fileType
	 * @return
	 */
	public Files findPhotoFile(Integer relationId,Integer fileType);
	/**
	 * 根据ID查询认证文件
	 * @param id
	 * @return
	 */
	public Files findPhotoFile(Integer id);
}
