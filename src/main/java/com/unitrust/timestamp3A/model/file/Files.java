package com.unitrust.timestamp3A.model.file;

import java.io.Serializable;

/**
 * 认证文件表
 * @author tsa02
 *
 */
public class Files implements Serializable{
	private static final long serialVersionUID = 5093279699864899166L;
	public Integer id;
	/**
	 * 个人用户ID/企业用户ID
	 */
	public Integer relationId;
	/**
	 * 类型 1个人/2企业
	 */
	public Integer registerType;
	/**
	 * 文件名
	 */
	public String fileName;
	/**
	 * 文件类型(0 手持证件照正面 1 个人身份证正面 2 个人身份证反面 3 企业营业执照 4 组织机构代码证照 5 法人手持证件照 6 代理人手持证件照 7税务登记证图片 8 统一社会信用代码图片)
	 */
	public Integer fileType;
	/**
	 * 文件
	 */
	private byte[] file;
	
	
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
}