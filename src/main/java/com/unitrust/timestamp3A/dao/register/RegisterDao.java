package com.unitrust.timestamp3A.dao.register;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.unitrust.timestamp3A.model.register.Register;

/**
 * 前端用户管理
 * @author tsa02
 *
 */
@Repository("registerDao")
public interface RegisterDao {
	/**
	 * 添加前端用户
	 * @param register
	 * @return
	 */
	public int save(Register register);
	/**
	 * 用户用户名账户查询
	 * @param loginCode
	 * @return
	 */
	public Register findUserByName(String loginCode);
	/**
	 * 邮箱账户查询
	 * @param loginCode
	 * @return
	 */
	public Register findEmailByName(String loginCode);
	/**
	 * 手机账户查询
	 * @param loginCode
	 * @return
	 */
	public Register findMobileByName(String loginCode);
	/**
	 * 根据用户ID查询登录信息
	 */
	public Register getRegisterById(Integer customerId);
	public List<Register> getListById(Integer customerId);
	/**
	 * 根据ID修改账号
	 * @param registerId
	 * @return
	 */
	public int modifyUser(Register register);
}
