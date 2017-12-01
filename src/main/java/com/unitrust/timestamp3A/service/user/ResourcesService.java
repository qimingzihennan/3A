package com.unitrust.timestamp3A.service.user;

import java.util.List;

import com.unitrust.timestamp3A.model.user.ResourceRoles;

public interface ResourcesService {

	/**
	 * 查询角色所分配权限信息
	 * 
	 * @param roleId
	 * @return
	 */
	public List<ResourceRoles> selectRolesRecourseByRoleId(String roleId);

	/**
	 * 添加角色权限信息
	 * 
	 * @param rr
	 */
	public void addRolesResources(ResourceRoles rr);

	/**
	 * 删除角色权限信息
	 * 
	 * @param rr
	 */
	public void deleteRolesResources(ResourceRoles rr);

	/**
	 * 获取用户权限信息
	 * 
	 * @param string
	 * @return
	 */
	public List<ResourceRoles> getUserResources(String userId);

}
