package com.unitrust.timestamp3A.service.business.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.dao.business.BusinessExtendFieldDao;
import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.service.business.BusinessExtendFieldService;

@Service("businessExtendFieldService")
public class BusinessExtendFieldServicelmpl implements BusinessExtendFieldService{
	
	@Resource
	private BusinessExtendFieldDao businessExtendFieldDao;

	@Override
	public List<BusinessExtendField> findByBkey(String bkey) {
		// TODO Auto-generated method stub
		return businessExtendFieldDao.findByBkey(bkey);
	}
	
}
