package com.unitrust.timestamp3A.model.user;

import java.io.Serializable;

/**
 * 用戶信息实体表
 * 
 * @author tsa04
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7970660230717422002L;
	/**
	 * 用户主键
	 */
	public Integer userId;
	/**
	 * 用户名
	 */
	public String userName;
	/**
	 * 密码
	 */
	public String password;
	/**
	 * 状态(0 停止 1 正常)
	 */
	public String status;
	public static final String USER_STATUS_STARTED = "1";
	public static final String USER_STATUS_STOPED = "0";

	/**
	 * 用户姓名
	 */
	public String realName;
	/**
	 * 性别(1 男 2 女)
	 */
	public String sex;
	public static final String USER_SEX_MALE = "1";
	public static final String USER_SEX_FEMALE = "2";
	/**
	 * 用户移动电话
	 */
	public String mobile;
	/**
	 * 用户名邮箱地址
	 */
	public String email;

	// // 一个集合roles，初始容量为0
	// private Set<Roles> roles = new HashSet<Roles>(0);

	/**
	 * 是否为超级管理员
	 */
	private String isSuper;

	/**
	 * 角色名称
	 */
	public String roleName;
	/**
	 * 角色id
	 */
	public Integer roleId;

	/**
	 * 删除标志位 0 代表未删除 1 代表已删除
	 */
	public Integer delFlag;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
