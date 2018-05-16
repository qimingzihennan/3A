package com.unitrust.timestamp3A.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.user.Roles;
import com.unitrust.timestamp3A.model.user.UserRoles;

/**
 * 角色信息Dao
 * 
 * @author hongwei
 *
 */
@Repository("rolesDao")
public interface RolesDao {

	public List<Roles> query(Page<Roles> page);

	public int save(Roles roles);

	public Roles findRolesById(String roleId);

	public int modifyRoles(Roles roles);

	public void remove(Integer roleId);

	public List<Roles> findAll();

	public void deleteUserRole(String string);

	public void saveUserRole(UserRoles userRoles);

}
