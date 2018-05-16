package com.unitrust.timestamp3A.service.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.user.User;

public interface UserService {

	/**
	 * 查询用户信息
	 * 
	 * @param page
	 * @return
	 */
	public List<User> query(Page<User> page);

	/**
	 * 用户信息添加
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public int save(User user) throws IOException;

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	public int remove(Integer id);
	public int removes(Integer id);

	/**
	 * 通过用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int modifyUser(User user);
	
	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int modifyPwd(User user);

	/**
	 * 通过用户查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByName(String userName);

}
