package com.unitrust.timestamp3A.model.order;

import java.io.Serializable;

/**
 * 订单拓展表
 * @author tsa02
 *
 */

public class OrderExtendField implements Serializable{
	
	private static final long serialVersionUID = -7556164813884542517L;

	private Integer oid; // 订单id

	private String bEFName; // 业务模块扩充字段名称

	private String bEFValue; // 业务字段值

	private String eName; // 业务模块扩充字段名称英文表示

	private String eValue; // IP字段

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getbEFName() {
		return bEFName;
	}

	public void setbEFName(String bEFName) {
		this.bEFName = bEFName;
	}

	public String getbEFValue() {
		return bEFValue;
	}

	public void setbEFValue(String bEFValue) {
		this.bEFValue = bEFValue;
	}

	

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteValue() {
		return eValue;
	}

	public void seteValue(String eValue) {
		this.eValue = eValue;
	}
	
}
