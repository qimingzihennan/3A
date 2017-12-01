package com.unitrust.timestamp3A.service.business;

import java.util.List;

import com.unitrust.timestamp3A.model.business.BusinessExtendField;

public interface BusinessExtendFieldService {
	public List<BusinessExtendField> findByBkey(String bkey);
}
