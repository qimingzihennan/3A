package com.unitrust.timestamp3A.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {

	public static final String REDIRECT_HOME = "/";
	public static final String LAST_PAGE = "unitrust.timestamp.lastPage";
	// private static final Base64 base64 = new Base64(true);

	public static final Map<String, String> Special_URL = new HashMap<String, String>() {
		{
			put("/main.do", "主页面");
			put("/menu/menu.do", "左侧菜单");
			put("/menu/head.do", "页头");
			put("/menu/index.do", "欢迎页");
			put("/menu/bottom.do", "页脚");
		}
	};

	/**
	 * 取出之前保存的请求
	 * 
	 * @return
	 */
	public static String retrieveSavedRequest() {
		HttpSession session = currentSession();
		if (session == null) {
			return REDIRECT_HOME;
		}
		String HashedlastPage = (String) session.getAttribute(LAST_PAGE);
		if (HashedlastPage == null) {
			return REDIRECT_HOME;
		} else {
			return retrieve(HashedlastPage);
		}
	}

	/**
	 * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
	 * 
	 * @return 当前session对象或{@code null}.
	 */
	public static HttpSession currentSession() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			return null;
		}
		return attrs.getRequest().getSession(false);
	}

	/**
	 * 解密请求的页面
	 * 
	 * @param targetPage
	 * @return
	 */
	public static String retrieve(String targetPage) {
		// byte[] decode = base64.decode(targetPage);
		// try {
		// String requestUri = new String(decode, "UTF-8");
		// int i = requestUri.indexOf("/", 1);
		// return requestUri.substring(i);
		// } catch (UnsupportedEncodingException ex) {
		// // this does not happen
		// return null;
		// }
		return targetPage;
	}

	/**
	 * 保存当前请求
	 */
	public static void saveRequest() {
		HttpServletRequest request = currentRequest();
		request.getSession().setAttribute(LAST_PAGE, RequestUtil.hashRequestPage(request));
		System.out.println("save request for {}" + request.getRequestURI());
	}

	/**
	 * 获取当前Request对象.
	 * 
	 * @return 当前Request对象或{@code null}.
	 * @throws IllegalStateException
	 *             当前线程不是web请求抛出此异常.
	 */
	public static HttpServletRequest currentRequest() throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			throw new IllegalStateException("当前线程中不存在 Request 上下文");
		}
		return attrs.getRequest();
	}

	/**
	 * 加密请求页面
	 * 
	 * @param request
	 * @return
	 */
	public static String hashRequestPage(HttpServletRequest request) {
		String reqUri = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null) {
			reqUri += "?" + query;
		}
		// String targetPage = null;
		// try {
		// targetPage = base64.encodeAsString(reqUri.getBytes("UTF-8"));
		// } catch (UnsupportedEncodingException ex) {
		// // this does not happen
		// }
		// return targetPage;
		return reqUri;
	}

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
