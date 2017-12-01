package com.unitrust.timestamp3A.service.personal;

import java.io.IOException;
import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.user.User;



public interface PersonalService {
	/**
	 * 查询用户信息
	 * 
	 * @param page
	 * @return
	 */
	public List<Personal> query(Page<Personal> page);
	/**
	 * 用户信息添加
	 * @param Personal
	 * @return
	 * @throws IOException
	 */
	public int save(Personal personal) throws IOException;
	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	public int remove(Integer id);
	/**
	 * 通过客户id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Personal getCustomById(Integer id);
	/**
	 * 修改用户信息
	 * 
	 * @param personal
	 * @return
	 */
	public int modifyUser(Personal personal);
	/**
	 * 根据用户身份证号获取客户信息
	 * @param idCard
	 * @return
	 */
	public Personal findUserByIdCard(String idCard);
	/**
	 * 用户邮箱查询
	 * @param email
	 * @return
	 */
	public Personal findUserByEmail(String email);
	/**
	 * 用户电话查询
	 * @param mobile
	 * @return
	 */
	public Personal findUserByMobile(String mobile);
	/**
	 * 修改认证状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int changeStatus(int id);
	public int changesStatus(int id);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delStatus(Integer id);
}
