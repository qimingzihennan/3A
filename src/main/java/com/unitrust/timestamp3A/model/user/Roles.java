package com.unitrust.timestamp3A.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：角色实体
 * 
 * @author hongwei.zhang
 * @date 创建时间：2016年6月23日 下午3:41:51
 * @version 1.0
 */
public class Roles implements Serializable {

	private static final long serialVersionUID = 4666097355915472833L;
	/**
	 * 角色id
	 */
	private Integer roleId;
	/**
	 * 是否禁用角色 1 表示禁用 2 表示不禁用
	 * 
	 * private Integer enable;
	 * 
	 * public static final Integer ROLES_ENABLE_DISABLE = 1; public static final
	 * Integer ROLES_ENABLE_ENABLE = 2;
	 */
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色关键字
	 * 
	 * private String roleKey;
	 */
	/**
	 * 角色描述
	 */
	private String description;
	/**
	 * 删除标志位 0 代表未删除 1 代表已删除
	 */
	public Integer delFlag;

	// public Integer getEnable() {
	// return enable;
	// }
	//
	// public void setEnable(Integer enable) {
	// this.enable = enable;
	// }

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public String getRoleKey() {
	// return roleKey;
	// }
	//
	// public void setRoleKey(String roleKey) {
	// this.roleKey = roleKey;
	// }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
