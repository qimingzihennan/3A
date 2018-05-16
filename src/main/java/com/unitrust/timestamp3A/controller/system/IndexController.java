package com.unitrust.timestamp3A.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unitrust.timestamp3A.model.user.ResourceRoles;

@Controller
@RequestMapping("/main")
public class IndexController {

	@RequestMapping("/index")
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("进入！");
		return "index";
	}

	/**
	 * @Title: toTop
	 * @Description: 加载顶部页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toTop")
	public String toTop(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "top";
	}

	/**
	 * @Title: toNavigation
	 * @Description: 加载功能导航页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toNavigation")
	public String toNavigation(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "navigation";
	}

	/**
	 * @Title: toLeftMenu
	 * @Description: 根据导航加载左侧菜单页
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toLeftMenu")
	public String toLeftMenu(String flag, HttpServletRequest request, HttpServletResponse response, Model model) {
		// HttpSession session = RequestUtil.currentSession();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<ResourceRoles> userRoleResouce = (List<ResourceRoles>) session.getAttribute("resourceRoles");

		List objectArrayList = new ArrayList();
		JSONObject jo = new JSONObject();
		Map<String, Object> menuMap = new HashMap<String, Object>();
		menuMap.put("menuid", 28);
		menuMap.put("icon", "icon-sys");
		menuMap.put("menuname", "功能菜单");
		Map<String, Object> childrenMenuMap = null;
		Object[] ob = new Object[userRoleResouce.size()];
		for (int i = 0; i < userRoleResouce.size(); i++) {
			ResourceRoles rr = userRoleResouce.get(i);
			childrenMenuMap = new HashMap<String, Object>();
			childrenMenuMap.put("menuname", rr.getResourcesName());
			childrenMenuMap.put("icon", "icon-nav");
			childrenMenuMap.put("url", rr.getUrl() + "list.do");
			ob[i] = childrenMenuMap;
		}
		menuMap.put("menus", ob);
		objectArrayList.add(menuMap);
		jo.put("menus", objectArrayList);
		// model.addAttribute("menu", menu_json);
		System.out.println(jo.toString());
		// System.out.println(menu_json);
		model.addAttribute("menu", jo.toString());
		return "leftmenu";
	}

	/**
	 * @Title: toBottom
	 * @Description: 加载顶部版权页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/toBottom")
	public String toBottom(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "bottom";
	}

}
