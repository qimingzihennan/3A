package com.unitrust.timestamp3A.dao.business;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.model.business.BusinessExtendField;


@Repository("businessExtendFieldDao")
public interface BusinessExtendFieldDao {
	
	public List<BusinessExtendField> findByBkey(String bkey);
}
