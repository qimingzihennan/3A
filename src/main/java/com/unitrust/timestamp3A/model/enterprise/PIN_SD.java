package com.unitrust.timestamp3A.model.enterprise;

import java.io.Serializable;

public class PIN_SD implements Serializable {

	/**
	 * 企业PIN码/SD码关联表
	 */
	private static final long serialVersionUID = 2693721582206985321L;
	
	/**
	 * 企业PIN码/SD码关联表主键id
	 */
	private Integer id;
	
	/**
	 * 企业id
	 */
	private Integer enterpriseId;
	
	/**
	 * 企业PIN码
	 */
	private String PIN;

	/**
	 * 企业SD码
	 */
	private String SD;
	
	/**
	 * 企业PIN码/SD码状态（0不可用，1可用）
	 */
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getSD() {
		return SD;
	}

	public void setSD(String sD) {
		SD = sD;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

