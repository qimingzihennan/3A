package com.unitrust.timestamp3A.model.user;

import java.io.Serializable;

/**
 * 说明：用户角色关系表
 * 
 * @author hongwei.zhang
 * @date 创建时间：2016年6月23日 下午3:48:25
 * @version 1.0
 */
public class UserRoles implements Serializable {

	private static final long serialVersionUID = -4697353779986139900L;
	private Integer roleId;
	private Integer userId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
