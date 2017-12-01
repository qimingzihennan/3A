package com.unitrust.timestamp3A.service.combo;

import java.util.List;

import com.unitrust.timestamp3A.model.combo.ComboExtendField;

public interface ComboExtendFieldService {
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
