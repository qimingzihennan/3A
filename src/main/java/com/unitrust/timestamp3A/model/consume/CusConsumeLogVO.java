package com.unitrust.timestamp3A.model.consume;

import java.util.Date;

public class CusConsumeLogVO extends CusConsumeLog {

	

	private static final long serialVersionUID = -6864426315495718898L;

	private String businessName;

	private String customerName;

	private String enterpriseName;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	

}
