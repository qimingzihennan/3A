package com.unitrust.timestamp3A.service.user.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.user.UserDao;
import com.unitrust.timestamp3A.model.user.User;
import com.unitrust.timestamp3A.service.user.UserService;
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Value("${timestamp3A.defaultPassword}")
	private String defaultPassword;
	
	@Override
	public int save(User user) throws IOException {
		// TODO Auto-generated method stub
		String passwordMD5 = DigestUtils.md5DigestAsHex(defaultPassword.getBytes());
		user.setPassword(passwordMD5);
		user.setStatus("1");
		user.setDelFlag(0);
		return userDao.save(user);
	}

	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		return userDao.delete(id);
	}
	@Override
	public int removes(Integer id) {
		// TODO Auto-generated method stub
		return userDao.deletes(id);
	}
	@Override
	public List<User> query(Page<User> page) {
		// TODO Auto-generated method stub
		return userDao.query(page);
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return userDao.modifyUser(user);
	}

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		
		return userDao.findUserByName(userName);
	}

	@Override
	public int modifyPwd(User user) {
		// TODO Auto-generated method stub
		return userDao.modifyPwd(user);
	}

}
