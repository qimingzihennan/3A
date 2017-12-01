package com.unitrust.timestamp3A.dao.file;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.model.file.Files;
import com.unitrust.timestamp3A.model.personal.Personal;
/**
 * 认证文件
 * @author tsa02
 *
 */
@Repository("fileDao")
public interface FileDao {
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
	public Files findPhotoFileById(Integer id);
}
