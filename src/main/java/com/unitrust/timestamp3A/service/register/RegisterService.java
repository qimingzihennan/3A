package com.unitrust.timestamp3A.service.register;

import java.io.IOException;
import java.util.List;

import com.unitrust.timestamp3A.model.register.Register;

public interface RegisterService {
	/**
	 * 添加前端用户
	 * @param register
	 * @return
	 */
	public int save(Register register) throws IOException;
	/**
	 * 用户用户名账户查询
	 * @param loginCode
	 * @param loginType
	 * @return
	 */
	public Register findUserByName(String loginCode);
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
	public int modifyUser(Register register);;
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
}
