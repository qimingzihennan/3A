package com.unitrust.timestamp3A.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.user.Roles;
import com.unitrust.timestamp3A.model.user.User;
import com.unitrust.timestamp3A.model.user.UserRoles;
import com.unitrust.timestamp3A.service.user.RoleService;
import com.unitrust.timestamp3A.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService rolesService;

	/**
	 * 用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "list")
	@SystemLog(module = "用户管理", methods = "进入用户查询页面")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "user/user";
	}

	@RequestMapping("/addUser")
	@SystemLog(module = "用户管理", methods = "进入用户添加页面")
	public String addRoles(String id, Model model) {
		model.addAttribute("iframeId", id);
		model.addAttribute("user", new User());
		return "user/addUser";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "用户管理", methods = "分页查询用户信息")
	public Map<String, Object> query(HttpServletRequest request, User user) {
		Page<User> page = new Page<User>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(user);
		page.setSearchCondition(paramMap);
		List<User> list = userService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}

	@RequestMapping("/save")
	@ResponseBody
	@SystemLog(module = "用户管理", methods = "保存用户信息")
	public Map<String, String> save(User user) {
		Map<String, String> map = new HashMap<>();
		int num = 0;
		try {
			User u = userService.findUserByName(user.getUserName());
			if (u != null) {
				map.put("msg", "用户名已存在");
				return map;
			}
			num = userService.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (num == 1) {
			map.put("msg", "添加成功");
		} else {
			map.put("msg", "添加失败");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	@SystemLog(module = "用户管理", methods = "冻结/启用用户信息")
	public ResultBean remove(Integer userId) {
		ResultBean result = new ResultBean();
		User user = userService.getUserById(userId.toString());
		try {
			if("0".equals(user.getStatus())){
				userService.removes(userId);
				result.putData("msg", "启用成功");
			}else{
				userService.remove(userId);
				result.putData("msg", "冻结成功");
			}
		} catch (Exception e) {
			result.putData("msg", "操作失败");
		}
		return result;
	}

	@RequestMapping("/toModify")
	@SystemLog(module = "用户管理", methods = "前往编辑用户信息页面")
	public String getUserList(String userId, String id, Model model) {
		User user = userService.getUserById(userId);
		model.addAttribute("iframeId", id);
		model.addAttribute("user", user);
		return "user/addUser";
	}

	@RequestMapping("/toModifyPwd")
	@SystemLog(module = "用户管理", methods = "前往修改用户密码页面")
	public String getUser(String userId, String id, Model model) {
		User user = userService.getUserById(userId);
		model.addAttribute("iframeId", id);
		model.addAttribute("user", user);
		return "user/modifyPwd";
	}
	@ResponseBody
	@RequestMapping("/modify")
	@SystemLog(module = "用户管理", methods = "编辑用户信息页面")
	public Map<String, Object> modify(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = userService.modifyUser(user);
		if (num == 1) {
			result.put("msg", "修改成功");
		} else {
			result.put("msg", "修改失败");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/modifyPwd")
	@SystemLog(module = "用户管理", methods = "修改用户密码页面")
	public Map<String, Object> modifyPwd(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		//MD5加密
		String pwd = user.getPassword();
		String passwords = DigestUtils.md5DigestAsHex(pwd.getBytes());
		user.setPassword(passwords);
		int num = userService.modifyPwd(user);
		if (num == 1) {
			result.put("msg", "修改成功");
		} else {
			result.put("msg", "修改失败");
		}
		return result;
	}
	
	/**
	 * 验证新密码是否跟旧密码相同
	 *  
	 *  */
	@ResponseBody
	@RequestMapping("/checkpwd")
	public String checkpwd(String userId,String password){
		User user = userService.getUserById(userId);
		String pwdold = user.getPassword();
		String pwdnew = DigestUtils.md5DigestAsHex(password.getBytes());
		if(pwdold.equals(pwdnew)){
			return "equal";
		}else{
			return "diff";
		}
	}
	
	@RequestMapping("/toModifySelfPwd")
	@SystemLog(module = "用户管理", methods = "前往修改自己密码页面")
	public String getSelfInfo(String id, Model model) {
		model.addAttribute("iframeId", id);
		
		return "user/modifyPwd";
	}
	
	@ResponseBody
	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public Map<String, Object> validateUser(String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		String errorCode = "1000";
		try {
			User user = userService.findUserByName(userName);
			if (user != null) {
				errorCode = "1001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		} finally {
			result.put("msg", errorCode);
		}
		return result;
	}

	/**
	 * 给用户分配角色界面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userRole")
	public Map<String, Object> userRole(Model model, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.getUserById(userId);
		map.put("user", user);
		List<Roles> roles = rolesService.findAll();
		map.put("roles", roles);
		return map;
	}

	/**
	 * 保存用户分配角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allocation")
	public Map<String, Object> allocation(Model model, UserRoles userRoles) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			rolesService.saveUserRole(userRoles);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败");
		}
		result.put("msg", "保存成功");
		return result;
	}

}
