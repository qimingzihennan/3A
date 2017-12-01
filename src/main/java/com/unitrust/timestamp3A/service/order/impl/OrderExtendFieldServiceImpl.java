package com.unitrust.timestamp3A.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitrust.timestamp3A.dao.order.OrderExtendFieldDao;
import com.unitrust.timestamp3A.model.order.OrderExtendField;
import com.unitrust.timestamp3A.service.order.OrderExtendFieldService;

@Service(value = "orderExtendFieldService")
public class OrderExtendFieldServiceImpl implements OrderExtendFieldService{
	
	@Autowired
	private OrderExtendFieldDao orderExtendFieldDao;

	@Override
	public int save(OrderExtendField oed) {
		// TODO Auto-generated method stub
		return orderExtendFieldDao.save(oed);
	}

}
