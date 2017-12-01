package com.unitrust.timestamp3A.service.user;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.user.Roles;
import com.unitrust.timestamp3A.model.user.UserRoles;


public interface RoleService {

	/**
	 * 查询角色列表信息
	 * 
	 * @param page
	 * @return
	 */
	public List<Roles> query(Page<Roles> page);

	/**
	 * 添加角色信息
	 * 
	 * @param roles
	 * @return
	 */
	public int save(Roles roles);

	/**
	 * 通过id查询角色信息
	 * 
	 * @param roleId
	 * @return
	 */
	public Roles findRolesById(String roleId);

	/**
	 * 更新角色信息
	 * 
	 * @param roles
	 * @return
	 */
	public int modifyRoles(Roles roles);

	/**
	 * 删除角色信息
	 * @param roleId
	 */
	public void remove(Integer roleId);

	/**
	 * 查询所有角色信息
	 * @return
	 */
	public List<Roles> findAll();

	/**
	 * 保存用户角色信息
	 * @param userRoles
	 */
	public void saveUserRole(UserRoles userRoles);

}
