package com.unitrust.timestamp3A.model.consume;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消费日志
 * 
 * @author tsa04
 *
 */
public class CusConsumeLog implements Serializable {

	public CusConsumeLog() {
	};

	public CusConsumeLog(Integer id, String cusIdOrPIN, Date operateTime, String cusType) {
		super();
		this.id = id;
		this.cusIdOrPIN = cusIdOrPIN;
		this.operateTime = operateTime;
		this.cusType = cusType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2204389781495016102L;

	private Integer id; // 用户消费清单表id

	private String cusIdOrPIN; // 客户ID或者PIN码，企业id

	private Date operateTime; // 操作时间

	private String cusType; // 个人/企业 person/enterprise
	
	public static final String CusConsumeLog_type_person = "person";
	public static final String CusConsumeLog_type_enterprise = "enterprise";

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCusIdOrPIN() {
		return cusIdOrPIN;
	}

	public void setCusIdOrPIN(String cusIdOrPIN) {
		this.cusIdOrPIN = cusIdOrPIN;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}
