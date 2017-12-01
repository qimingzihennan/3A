package com.unitrust.timestamp3A.service.business;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.business.Business;

import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.model.business.BusinessVO;

public interface BusinessService {

	public List<Business> query(Page<Business> page);
	public List<Business> query();

	public List<BusinessExtendField> listBusinessExtendField(String Bkey);

	public void remove(String Bkey);

	public List<BusinessVO> queryTest(Page<Business> page);
	public Business findById(String bkey);

}
