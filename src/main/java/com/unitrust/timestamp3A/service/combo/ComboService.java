package com.unitrust.timestamp3A.service.combo;

import java.io.IOException;
import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.personal.Personal;

public interface ComboService {

	/**
	 * 
	 * @return 套餐列表
	 */
	public List<Combo> queryList(Page<Combo> page);	
	/**
	 * 
	 * @return 套餐列表
	 */
	public List<Combo> query();
	/**
	 * 个人套餐
	 * @return
	 */
	public List<Combo> querys();
	/**
	 * 企业套餐
	 * @return
	 */
	public List<Combo> querye();
	/**
	 * 新增套餐
	 * @param combo
	 * @return
	 */
	public int save(Combo combo);
	/**
	 * 修改套餐
	 * @param combo
	 * @return
	 */
	public int modify(Combo combo);
	/**
	 * 删除套餐
	 * @param combo
	 * @return
	 */
	public int deleStatus(Integer id,Integer status);
	/**
	 * 修改套餐状态
	 * @param combo
	 * @return
	 */
	public int changeStatus(Integer id,Integer status);
	/**
	 * 校验套餐真实性
	 * @param id
	 * @return
	 */
	public int checkout(Integer id);
	/**
	 * 通过用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Combo getComboById(Integer id);
}
