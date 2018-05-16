package com.unitrust.timestamp3A.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unitrust.timestamp3A.model.user.ResouceConfig;
import com.unitrust.timestamp3A.model.user.ResourceRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import unitrust.sso.config.ConfigProperties;

public class Common {

	private static final Logger logger = LoggerFactory.getLogger(Common.class);

	@Autowired
	private ConfigProperties configProperties;

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map ObjectToMap(Object obj) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (obj == null) {
			return returnMap;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			Object value = getFieldValueByName(name, obj);
			if (value instanceof String) {
				if (Common.isEmpty((String) value)) {
					continue;
				}
			}
			if (value != null) {
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}

	/**
	 * 通过属性名称获取属性值
	 * 
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] a) {
		LinkedList<HashMap<String, String>> list = createResourcesList();
		for (HashMap<String, String> mapValue : list) {
			System.out.println("showName:" + mapValue.get("showName"));
			System.out.println("url:" + mapValue.get("url"));
			System.out.println("menu:" + mapValue.get("menu"));
		}
	}

	/**
	 * 生成功能资源链表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static LinkedList<HashMap<String, String>> createResourcesList() {
		ResouceConfig rconf = new ResouceConfig();
		Field[] fields = rconf.getClass().getDeclaredFields();
		LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
		try {
			Class ownerClass = Class.forName("com.unitrust.timestamp3A.model.user.ResouceConfig");
			for (Field field : fields) {
				HashMap value = (HashMap) field.get(ownerClass);
				String urlFromAR = (String) value.get("url");
				if (!Common.isEmpty(urlFromAR)) {
					String key = urlFromAR.replace("/", "").replace(".", "");
					value.put("id", key);
				}
				list.add(value);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static LinkedList<HashMap<String, String>> getMenuList() {
		ResouceConfig rconf = new ResouceConfig();
		Field[] fields = rconf.getClass().getDeclaredFields();
		LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
		try {
			Class ownerClass = Class.forName("qingdao.zhongcaiwei.common.ResouceConfig");
			for (Field field : fields) {
				HashMap value = (HashMap) field.get(ownerClass);
				String menu = (String) value.get("menu");
				if (menu.equals("1")) {
					list.add(value);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 复制一个HashMap
	 * 
	 * @param obj
	 * @return
	 */
	public static HashMap createCopyMap(Map obj) {
		Set keys = obj.keySet();
		HashMap newMap = new HashMap();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = obj.get(key);
			newMap.put(key, value);
		}
		return newMap;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 判断请求的浏览器类型
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (UserAgent != null) {
			if (UserAgent.indexOf("msie") >= 0)
				return "IE";
			if (UserAgent.indexOf("firefox") >= 0)
				return "FF";
			if (UserAgent.indexOf("safari") >= 0)
				return "SF";
		}
		return null;
	}

	public static File getFileFromBytes(byte[] b, String outputFileName) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFileName);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	public static List<ResourceRoles> getAllResources(Integer roleId) {
		ResouceConfig rconf = new ResouceConfig();
		Field[] fields = rconf.getClass().getDeclaredFields();
		List<ResourceRoles> list = new ArrayList<ResourceRoles>();
		try {
			Class ownerClass = Class.forName("com.unitrust.timestamp3A.model.user.ResouceConfig");
			for (Field field : fields) {
				HashMap value = (HashMap) field.get(ownerClass);
				String menu = (String) value.get("menu");
				String url = (String) value.get("url");
				String showName = (String) value.get("showName");
				// if (menu.equals("2")) {
				ResourceRoles rr = new ResourceRoles();
				rr.setRoleId(roleId);
				rr.setUrl(url);
				rr.setResourcesName(showName);
				list.add(rr);
				// }
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String getBkeyDictionariesFromProperties(String key) throws Exception {
		String mark = "";
		Properties prop = null;
		try {
			prop = configProperties.getObject();
		} catch (IOException e) {
			// e.printStackTrace();
			logger.debug("加载业务系统订单标记配置文件失败！");
			return "UN";
		}
		mark = prop.getProperty(key);
		if (isEmpty(mark)) {
			mark = "UN";
		}
		return mark;
	}
}
