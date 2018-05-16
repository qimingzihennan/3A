package com.unitrust.timestamp3A.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.dao.user.ResourcesDao;
import com.unitrust.timestamp3A.model.user.ResourceRoles;
import com.unitrust.timestamp3A.service.user.ResourcesService;
@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

	@Resource
	private ResourcesDao resourcesDao;

	@Override
	public List<ResourceRoles> selectRolesRecourseByRoleId(String roleId) {
		// TODO Auto-generated method stub
		List<ResourceRoles> resultList = resourcesDao.selectRolesRecourseByRoleId(roleId);
		return resultList;
	}

	@Override
	public void addRolesResources(ResourceRoles rr) {
		// TODO Auto-generated method stub
		Integer roleId = rr.getRoleId();
		String url = rr.getUrl();
		List<ResourceRoles> list = Common.getAllResources(rr.getRoleId());
		for (ResourceRoles rrs : list) {
			if (url.equals(rrs.getUrl()) && roleId == rrs.getRoleId()) {
				rr.setResourcesName(rrs.getResourcesName());
			}
		}
		resourcesDao.addRolesResources(rr);
	}

	@Override
	public void deleteRolesResources(ResourceRoles rr) {
		// TODO Auto-generated method stub
		resourcesDao.deleteRolesResources(rr);
	}

	@Override
	public List<ResourceRoles> getUserResources(String userId) {
		// TODO Auto-generated method stub
		List<ResourceRoles> list = resourcesDao.getUserResources(userId);
		return list;
	}

}
