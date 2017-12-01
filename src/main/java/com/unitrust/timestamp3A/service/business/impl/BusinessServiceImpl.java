package com.unitrust.timestamp3A.service.business.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.business.BusinessDao;
import com.unitrust.timestamp3A.model.business.Business;

import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.model.business.BusinessVO;
import com.unitrust.timestamp3A.service.business.BusinessService;

@Service(value = "businessService")
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessDao businessDao;

	
	@Override
	public List<BusinessExtendField> listBusinessExtendField(String Bkey) {
		// TODO Auto-generated method stub
		return businessDao.listBusinessExtendField(Bkey);
	}

	@Override
	public void remove(String Bkey) {
		// TODO Auto-generated method stub
		businessDao.remove(Bkey);
	}

	@Override
	public List<BusinessVO> queryTest(Page<Business> page) {
		// TODO Auto-generated method stub
		return businessDao.queryTest(page);
	}

	@Override
	public List<Business> query(Page<Business> page) {
		// TODO Auto-generated method stub
		return businessDao.query(page);
	}

	@Override
	public List<Business> query() {
		// TODO Auto-generated method stub
		return businessDao.query();
	}

	@Override
	public Business findById(String bkey) {
		// TODO Auto-generated method stub
		return businessDao.findById(bkey);
	}

}
