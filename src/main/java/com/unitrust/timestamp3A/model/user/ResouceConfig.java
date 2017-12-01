package com.unitrust.timestamp3A.model.user;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能信息注册页面
 * 
 * @author tsa04
 *
 */
public class ResouceConfig {
	/***
	 * 所有功能按照以下格式进行注册 showName：功能名称 url：请求访问url menu属于哪一级别 菜单命名规则：
	 * 导航菜单命名规则：实体类名，例如：USER ;
	 * showName:菜单名，例如：用户管理;url为列表访问url，例如：/user/list.do;＊menu:1
	 * 子菜单命名规则:实体类名+_+任意字符 menu:2
	 */

	@SuppressWarnings("serial")
	public static final Map<String, String> USER = new HashMap<String, String>() {
		{
			put("showName", "用户管理");
			put("url", "/user/");
			put("menu", "1");
		}
	};
	// 角色菜单相关信息 start from this

	@SuppressWarnings("serial")
	public static final Map<String, String> ROLE = new HashMap<String, String>() {
		{
			put("showName", "角色管理");
			put("url", "/roles/");
			put("menu", "1");
		}
	};


	
	/*@SuppressWarnings("serial")
	public static final Map<String, String> BUSINESS = new HashMap<String, String>() {
		{
			put("showName", "业务管理");
			put("url", "/business/");
			put("menu", "1");
		}
	};*/


	@SuppressWarnings("serial")
	public static final Map<String, String> CUSTOM = new HashMap<String, String>() {
		{
			put("showName", "个人用户管理");
			put("url", "/personal/");
			put("menu", "1");
		}
	};

	@SuppressWarnings("serial")
	public static final Map<String, String> ORDER = new HashMap<String, String>() {
		{
			put("showName", "订单管理");
			put("url", "/order/");
			put("menu", "1");
		}
	};
	
	@SuppressWarnings("serial")
	public static final Map<String, String> CONSUME = new HashMap<String, String>() {
		{
			put("showName", "用户使用情况");
			put("url", "/consume/cci/");
			put("menu", "1");
		}
	};
	
	@SuppressWarnings("serial")
	public static final Map<String, String> CONSUMELOG_Person = new HashMap<String, String>() {
		{
			put("showName", "个人用户操作日志");
			put("url", "/consume/log/person/");
			put("menu", "1");
		}
	};
	
	@SuppressWarnings("serial")
	public static final Map<String, String> CONSUMELOG_enterprise = new HashMap<String, String>() {
		{
			put("showName", "企业用户操作日志");
			put("url", "/consume/log/enterprise/");
			put("menu", "1");
		}
	};
	
	@SuppressWarnings("serial")
	public static final Map<String, String> LOG = new HashMap<String, String>() {
		{
			put("showName", "用户操作日志");
			put("url", "/logs/");
			put("menu", "1");
		}
	};
	
	

	@SuppressWarnings("serial")
	public static final Map<String, String> COMBO = new HashMap<String, String>() {
		{
			put("showName", "套餐管理");
			put("url", "/combo/");
			put("menu", "1");
		}
	};
	
	@SuppressWarnings("serial")
	public static final Map<String, String> ENTERPRISE = new HashMap<String, String>() {
		{
			put("showName", "企业管理");
			put("url", "/enterprise/");
			put("menu", "1");
		}
	};
	@SuppressWarnings("serial")
	public static final Map<String, String> TASKLIST = new HashMap<String, String>() {
		{
			put("showName", "定时器管理");
			put("url", "/task/");
			put("menu", "1");
		}
	};

}
