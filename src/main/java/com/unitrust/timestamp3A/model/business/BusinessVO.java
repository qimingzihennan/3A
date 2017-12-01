package com.unitrust.timestamp3A.model.business;

import java.io.Serializable;
import java.util.List;

public class BusinessVO implements Serializable {

	private static final long serialVersionUID = -2103436856073104348L;
	private String businessName; // 业务模块名称

	private String businessDes;// 业务模块描述

	private String Bkey;// 业务模块key

	private String del; // 删除标志 1 已删除

	private List<BusinessExtendField> businessExtendFieldList;

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

	public String getBkey() {
		return Bkey;
	}

	public void setBkey(String bkey) {
		Bkey = bkey;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public List<BusinessExtendField> getBusinessExtendFieldList() {
		return businessExtendFieldList;
	}

	public void setBusinessExtendFieldList(List<BusinessExtendField> businessExtendFieldList) {
		this.businessExtendFieldList = businessExtendFieldList;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BusinessVO [businessName=" + businessName + ", businessDes=" + businessDes + ", bb_Bkey=" + Bkey + "]";
	}

	public BusinessVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessVO(String businessName, String businessDes, String Bkey, String del,
			List<BusinessExtendField> businessExtendFieldList) {
		super();
		this.businessName = businessName;
		this.businessDes = businessDes;
		this.Bkey = Bkey;
		this.del = del;
		this.businessExtendFieldList = businessExtendFieldList;
	}

}
