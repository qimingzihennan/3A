package com.unitrust.timestamp3A.model.combo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 套餐字段扩展表
 * 
 * @author tsa04
 *
 */
public class ComboExtendField implements Serializable {

	private static final long serialVersionUID = 2919882048772703990L;

	private Integer coId; // 套餐id


	private String bEFName; // 业务模块扩充字段名称

	private String bEFValue; // 业务字段值
	
	private String eName; //业务模块扩充字段名称英文表示
	
	


	public Integer getCoId() {
		return coId;
	}

	public void setCoId(Integer coId) {
		this.coId = coId;
	}



	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
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


}
