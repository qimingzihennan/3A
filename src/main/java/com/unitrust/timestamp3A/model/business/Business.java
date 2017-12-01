package com.unitrust.timestamp3A.model.business;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;


/**
 * 业务模块
 * 
 * @author tsa04
 *
 */
public class Business implements Serializable {

	private static final long serialVersionUID = -7943655650944432189L;

	private String businessName; // 业务模块名称

	private String businessDes;// 业务模块描述


	private String bkey;// 业务模块key

	private String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBkey() {
		return bkey;
	}

	public void setBkey(String bkey) {
		this.bkey = bkey;
	}

	private String del; // 删除标志 1 已删除

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessDes() {
		return businessDes;
	}

	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}



	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

}
