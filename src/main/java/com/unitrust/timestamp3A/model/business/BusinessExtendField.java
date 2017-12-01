package com.unitrust.timestamp3A.model.business;

import java.io.Serializable;

/**
 * 业务模块扩充字段
 * 
 * @author tsa04
 *
 */


public class BusinessExtendField implements Serializable{
	private static final long serialVersionUID = -7943655650944432189L;
	private String bEFName;//业务模块扩充字段名称
	
	private String bkey;//业务模块key
	
	private String eName;//业务模块扩充字段名称英文表示
	
	
	
	public String getbEFName() {
		return bEFName;
	}
	public void setbEFName(String bEFName) {
		this.bEFName = bEFName;
	}
	public String getBkey() {
		return bkey;
	}
	public void setBkey(String bkey) {
		this.bkey = bkey;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}

}
