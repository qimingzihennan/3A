package com.unitrust.timestamp3A.redis.model;

import java.io.Serializable;

public class OrderExtVO implements Serializable {

	private static final long serialVersionUID = -5240814002098650386L;

	private String BEFName;

	private String eName;

	private String eValue;

	public String getBEFName() {
		return BEFName;
	}

	public void setBEFName(String bEFName) {
		BEFName = bEFName;
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
