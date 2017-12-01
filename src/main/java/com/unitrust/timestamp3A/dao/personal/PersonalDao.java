package com.unitrust.timestamp3A.dao.personal;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.user.User;




/**
 * 个人用户dao
 * 
 * @author hongwei
 *
 */
@Repository("personalDao")
public interface PersonalDao {
	/**
	 * 查询所有用户信息
	 * @param page
	 * @return
	 */
	public List<Personal> query(Page<Personal> page);
	/**
	 * 添加用户
	 * @param personal
	 * @return
	 */
	public int save(Personal personal);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	public Personal getCustomById(Integer id);
	/**
	 * 修改用户信息
	 * @param personal
	 * @return
	 */
	public int modifyUser(Personal personal);
	/**
	 * 用户身份证号
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
