package com.unitrust.timestamp3A.model.order;

public class OrderVO extends Order{
	private static final long serialVersionUID = -1940367311102394550L;


	private String cName;

	private String enterpriseName;

	private String customerName;

	public String getcName() {
		String name =this.getCustomerName();
		if (name == null || "".equals(name)) {
			cName = this.getEnterpriseName();
		} else {
			cName = this.getCustomerName();
		}
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
