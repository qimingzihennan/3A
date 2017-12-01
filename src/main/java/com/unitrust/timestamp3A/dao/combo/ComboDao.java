package com.unitrust.timestamp3A.dao.combo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.personal.Personal;



@Repository("comboDao")
public interface ComboDao {
	
	
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
	 * 个人用户套餐
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
	 * 根据ID查询状态可用的
	 * @param id
	 * @return
	 */
	public Combo findById(Integer id);
	/**
	 * 通过用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Combo getComboById(Integer id);
}
