package com.unitrust.timestamp3A.model.register;

import java.io.Serializable;
/**
 * 前端用户管理
 * @author tsa02
 *
 */
import java.util.Date;
public class Register implements Serializable{
	private static final long serialVersionUID = 5093279699864899166L;
	/**
	 * 注册用户ID
	 */
	public Integer registerId;
	/**
	 * 用户ID
	 */
	public Integer customerId;
	/**
	 * 登录类型 0账号登录 1邮箱登录 2手机登录
	 */
	public int loginType;
	/**
	 * 登录账号
	 */
	public String loginCode;
	/**
	 * 密码
	 */
	public String password;
	/**
	 * 注册时间
	 */
	public Date createTime;
	/**
	 * 状态(1未激活 2激活）
	 */
	public int status;
	public Integer getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
