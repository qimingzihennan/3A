package com.unitrust.timestamp3A.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.model.user.ResourceRoles;

@Repository("resourcesDao")
public interface ResourcesDao {

	public List<ResourceRoles> selectRolesRecourseByRoleId(String roleId);

	public void addRolesResources(ResourceRoles rr);

	public void deleteRolesResources(ResourceRoles rr);

	public List<ResourceRoles> getUserResources(String userId);

}
