package com.unitrust.timestamp3A.model.user;

/**
 * 说明：资源与角色关系实体
 * 
 * @author hongwei.zhang
 * @date 创建时间：2016年6月23日 下午3:41:51
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ResourceRoles implements java.io.Serializable {

	/**
	 * 角色id
	 */
	private Integer roleId;
	// private Integer rescId;

	/**
	 * Controller访问地址
	 */
	private String url;

	private String resourcesName;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	

}