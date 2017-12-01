package com.unitrust.timestamp3A.dao.business;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.business.Business;
import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.model.business.BusinessVO;
import com.unitrust.timestamp3A.model.combo.Combo;

@Repository(value = "businessDao")
public interface BusinessDao {

	/**
	 * 查询业务信息(翻页)
	 * 
	 * @param page
	 * @return
	 */
	public List<Business> query(Page<Business> page);
	public List<Business> query();

	/**
	 * 查询业务信息扩展字段By业务信息Bkey
	 * 
	 * @param id
	 * @return
	 */
	public List<BusinessExtendField> listBusinessExtendField(String Bkey);

	/**
	 * 通过Bkey删除业务信息，修改flag值
	 * 
	 * @param Bkey
	 */
	public void remove(String Bkey);

	public List<BusinessVO> queryTest(Page<Business> page);

	public Business findById(String bkey);
}
