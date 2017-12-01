package com.unitrust.timestamp3A.controller.user;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.user.ResourceRoles;
import com.unitrust.timestamp3A.service.user.ResourcesService;

@Controller
@RequestMapping("roles/resources")
public class ResourcesController {

	@Resource
	private ResourcesService resourcesService;

	/**
	 * 某个角色拥有的权限
	 * 
	 * @param model
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "permissioRole")
	@SystemLog(module = "权限管理", methods = "查看角色拥有的权限")
	public Map<String, Object> permissioRole(Model model, String roleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取所有功能
		LinkedList<HashMap<String, String>> allRecourses = Common.createResourcesList();
		// 获取角色拥有功能
		List<ResourceRoles> rolesResources = resourcesService.selectRolesRecourseByRoleId(roleId);
		// 生成展示列表
		for (ResourceRoles rr : rolesResources) {
			String url = rr.getUrl();
			for (int i = 0; i < allRecourses.size(); i++) {
				HashMap ar = allRecourses.get(i);
				HashMap newMap = Common.createCopyMap(ar);
				String arURL = (String) newMap.get("url");
				if (url.equals(arURL)) {
					newMap.put("checked", "checked");
					allRecourses.set(i, newMap);
				}

			}
		}
		result.put("resouces", allRecourses);
		result.put("roleId", roleId);
		return result;
	}

	/**
	 * 更新角色功能配置
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateRolesResources")
	@SystemLog(module = "权限管理", methods = "更新角色功能配置")
	public String updateRolesResources(Model model, String url, String roleId, String value) {
		ResourceRoles rr = new ResourceRoles();
		rr.setRoleId(Integer.valueOf(roleId));
		rr.setUrl(url);
		if (value.equals("1")) {
			resourcesService.addRolesResources(rr);
		} else {
			resourcesService.deleteRolesResources(rr);
		}
		return null;
	}

}
