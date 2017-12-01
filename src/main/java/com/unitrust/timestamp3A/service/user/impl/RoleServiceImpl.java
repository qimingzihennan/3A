package com.unitrust.timestamp3A.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.user.RolesDao;
import com.unitrust.timestamp3A.model.user.Roles;
import com.unitrust.timestamp3A.model.user.UserRoles;
import com.unitrust.timestamp3A.service.user.RoleService;


@Transactional
@Service("rolesService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RolesDao rolesDao;

	@Override
	public List<Roles> query(Page<Roles> page) {
		// TODO Auto-generated method stub
		return rolesDao.query(page);
	}

	@Override
	public int save(Roles roles) {
		// TODO Auto-generated method stub
		roles.setDelFlag(0);
		return rolesDao.save(roles);
	}

	@Override
	public Roles findRolesById(String roleId) {
		// TODO Auto-generated method stub
		return rolesDao.findRolesById(roleId);
	}

	@Override
	public int modifyRoles(Roles roles) {
		// TODO Auto-generated method stub
		return rolesDao.modifyRoles(roles);
	}

	@Override
	public void remove(Integer roleId) {
		// TODO Auto-generated method stub
		 rolesDao.remove(roleId);
	}

	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return rolesDao.findAll();
	}

	@Override
	public void saveUserRole(UserRoles userRoles) {
		// TODO Auto-generated method stub
		rolesDao.deleteUserRole(userRoles.getUserId().toString());
		rolesDao.saveUserRole(userRoles);
	}
}
