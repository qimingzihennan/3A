package com.unitrust.timestamp3A.model.excuteLog;

import java.io.Serializable;
import java.util.Date;

public class ExcuteLog implements Serializable{
	private static final long serialVersionUID = -6961371731646220346L;
	private Integer id;//日志ID

	private String module; // 操作模块

	private String methods; // 操作方法

	private String description; // 异常描述

	private String actionTime; // 操作用时

	private Date nowTime;// 操作时间

	private Integer taskId;
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}
	
}
