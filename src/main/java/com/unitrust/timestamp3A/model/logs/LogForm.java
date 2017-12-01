package com.unitrust.timestamp3A.model.logs;

import java.io.Serializable;
import java.util.Date;

public class LogForm implements Serializable {

	private static final long serialVersionUID = -6961371731646220346L;
	private Integer id;

	private String accountName; // 方法操作人

	private String module; // 操作模块

	private String methods; // 操作方法

	private String description; // 操作描述

	private String actionTime; // 操作用时

	private String userIP; // 用户ip

	private Date nowTime;// 操作时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}

}
