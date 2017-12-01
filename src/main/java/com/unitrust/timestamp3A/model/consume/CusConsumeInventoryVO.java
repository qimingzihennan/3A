package com.unitrust.timestamp3A.model.consume;

public class CusConsumeInventoryVO extends CusConsumeInventory {
	

	private static final long serialVersionUID = -1940367311102394550L;

	private String orderNO;

	private String cName;

	private String enterpriseName;

	private String customerName;

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getcName() {
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
