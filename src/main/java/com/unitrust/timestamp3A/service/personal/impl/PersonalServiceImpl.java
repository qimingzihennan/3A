package com.unitrust.timestamp3A.service.personal.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.personal.PersonalDao;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.service.personal.PersonalService;


@Service("personalService")
public class PersonalServiceImpl implements PersonalService {

	@Resource
	private PersonalDao personalDao;

	@Override
	public List<Personal> query(Page<Personal> page) {
		// TODO Auto-generated method stub
		List<Personal> personalList = personalDao.query(page);
		return personalList;
	}
	@Override
	public List<Personal> querys(Page<Personal> page) {
		// TODO Auto-generated method stub
		List<Personal> personalList = personalDao.querys(page);
		return personalList;
	}

	@Override
	public int save(Personal personal) throws IOException {
		// TODO Auto-generated method stub
		
		return personalDao.save(personal);
	}

	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		return personalDao.delete(id);
	}

	@Override
	public Personal getCustomById(Integer id) {
		// TODO Auto-generated method stub
		return personalDao.getCustomById(id);
	}
	@Override
	public Personal getCustomidById(Integer id) {
		// TODO Auto-generated method stub
		return personalDao.getCustomidById(id);
	}
	@Override
	public int modifyUser(Personal personal) {
		// TODO Auto-generated method stub
		return personalDao.modifyUser(personal);
	}

	

	@Override
	public Personal findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return personalDao.findUserByEmail(email);
	}

	@Override
	public Personal findUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return personalDao.findUserByMobile(mobile);
	}

	@Override
	public Personal findUserByIdCard(String idCard) {
		// TODO Auto-generated method stub
		return personalDao.findUserByIdCard(idCard);
	}

	@Override
	public int changeStatus(int id) {
		// TODO Auto-generated method stub
		return personalDao.changeStatus(id);
	}
	
	@Override
	public int changesStatus(int id) {
		// TODO Auto-generated method stub
		return personalDao.changesStatus(id);
	}

	@Override
	public int delStatus(Integer id) {
		// TODO Auto-generated method stub
		return personalDao.delStatus(id);
	}
	@Override
	public int delsStatus(Integer id) {
		// TODO Auto-generated method stub
		return personalDao.delsStatus(id);
	}
	


}
