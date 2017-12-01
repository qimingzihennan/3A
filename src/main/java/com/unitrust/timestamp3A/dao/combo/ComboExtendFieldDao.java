package com.unitrust.timestamp3A.dao.combo;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.unitrust.timestamp3A.model.combo.ComboExtendField;

@Repository("comboExtendFieldDao")
public interface ComboExtendFieldDao {
	/**
	 * 新增套餐扩展字段
	 * @param comboExtendFieId
	 * @return
	 */
//	public int save(List<ComboExtendField> list);
	public int save(ComboExtendField cef);
	/**
	 * 修改
	 */
	public int modify(ComboExtendField comboExtendField);
	/**
	 * ID查询
	 */
	public List<ComboExtendField> findById(Integer id);
	
}
