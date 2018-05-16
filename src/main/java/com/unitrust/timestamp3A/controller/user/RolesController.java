package com.unitrust.timestamp3A.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.user.Roles;
import com.unitrust.timestamp3A.service.user.RoleService;

@Controller
@RequestMapping("/roles")
public class RolesController {

	@Resource
	private RoleService rolesService;

	@RequestMapping(value = "list")
	@SystemLog(module = "角色管理", methods = "前往角色信息页面")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "user/roles";
	}

	@RequestMapping("/addRoles")
	@SystemLog(module = "角色管理", methods = "前往角色信息添加页面")
	public String addRoles(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "user/addRoles";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "角色管理", methods = "分页查询角色信息")
	public Map<String, Object> query(HttpServletRequest request, Roles roles) {
		Page<Roles> page = new Page<Roles>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(roles);
		page.setSearchCondition(paramMap);
		List<Roles> list = rolesService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}

	@RequestMapping("/save")
	@ResponseBody
	@SystemLog(module = "角色管理", methods = "保存角色信息")
	public Map<String, String> save(Roles roles) {
		Map<String, String> map = new HashMap<>();
		int num = rolesService.save(roles);
		if (num == 1) {
			map.put("msg", "添加成功");
		} else {
			map.put("msg", "添加失败");
		}
		return map;
	}

	@RequestMapping("/toModify")
	@SystemLog(module = "角色管理", methods = "前往角色信息编辑页面")
	public String findRolesById(String roleId, String id, Model model) {
		Roles roles = rolesService.findRolesById(roleId);
		model.addAttribute("iframeId", id);
		model.addAttribute("roles", roles);
		return "user/addRoles";
	}

	@ResponseBody
	@RequestMapping("/modify")
	@SystemLog(module = "角色管理", methods = "编辑角色信息")
	public Map<String, Object> modify(Roles roles) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = rolesService.modifyRoles(roles);
		if (num == 1) {
			result.put("msg", "修改成功");
		} else {
			result.put("msg", "修改失败");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/delete")
	@SystemLog(module = "角色管理", methods = "删除角色信息")
	public ResultBean remove(Integer roleId) {
		ResultBean result = new ResultBean();
		result.putData("msg", "删除成功");
		try {
			rolesService.remove(roleId);
		} catch (Exception e) {
			result.putData("msg", "删除失败");
		}
		return result;
	}

}
