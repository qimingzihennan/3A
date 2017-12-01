package com.unitrust.timestamp3A.dao.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.user.ResourceRoles;
import com.unitrust.timestamp3A.model.user.User;

@Repository("userDao")
public interface UserDao {

	public List<User> query(Page<User> page);

	public int save(User user);

	public int delete(Integer id);

	public User getUserById(String userId);

	public int modifyUser(User user);

	public User findUserByName(String userName);

	public List<ResourceRoles> getUserResources(String userId);
	
	public int modifyPwd(User user);

}
