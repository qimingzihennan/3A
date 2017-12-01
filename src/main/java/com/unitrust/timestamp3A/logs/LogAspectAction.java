package com.unitrust.timestamp3A.logs;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.RequestUtil;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.dao.log.LoggerDao;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.model.user.User;

@Aspect
public class LogAspectAction {
	// 本地异常日志记录对象
	private static final Logger log = LoggerFactory.getLogger(LogAspectAction.class);

	@Resource
	private LoggerDao logger;

	@Pointcut(value = "@annotation(com.unitrust.timestamp3A.authorization.SystemLog)")
	public void controllerPointCut() {
		// System.out.println("pointCust");
	}

	@AfterReturning(pointcut = "controllerPointCut()")
	public void doBefore(JoinPoint joinPoint) {
		// System.out.println("doBefore");
	}

	@AfterThrowing(value = "controllerPointCut()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		LogForm logForm = new LogForm();
		Map<String, Object> map = null;
		User user = null;
		String userMsg = "";
		String ip = null;
		HttpServletRequest request = RequestUtil.currentRequest();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			ip = RequestUtil.getIpAddr(request);
		} catch (Exception e2) {
			// TODO: handle exception
			ip = "无法获取登录用户Ip";
		}

		try {
			map = getControllerMethodDescription(joinPoint);
			// 登录名
			// user = SecurityUtils.getSubject().getPrincipal().toString();
			user = (User) session.getAttribute("userModel");
			if (user == null) {
				userMsg = "无法获取登录用户信息！";
			} else {
				userMsg = "用户名：" + user.getUserName() + " ,真实姓名:" + user.getRealName();
			}
		} catch (Exception ee) {
			userMsg = "无法获取登录用户信息！";
			log.error("====通知异常====");
			log.error("异常信息:{}", userMsg);
		}

		logForm.setAccountName(userMsg);
		logForm.setModule((String) map.get("module"));
		String methods = "<font color=\"red\">执行方法异常:-->" + map.get("methods") + "</font>";
		logForm.setMethods(methods);
		String description = "<font color=\"red\">执行方法异常:-->" + e + "</font>";
		logForm.setDescription(description);
		logForm.setActionTime("0");
		logForm.setUserIP(ip);
		try {
			logger.add(logForm);
		} catch (Exception e1) {
			log.error("====通知异常====");
			log.error("异常信息:{}", e1.getMessage());
		}

	}

	@Around(value = "controllerPointCut()")
	public Object doController(ProceedingJoinPoint point) {
		Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		LogForm logForm = new LogForm();
		Map<String, Object> map = null;
		User user = null;
		String userMsg = null;
		Long start = 0L;
		Long end = 0L;
		// Long time = 0L;
		String ip = null;
		HttpServletRequest request = RequestUtil.currentRequest();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Double t = 0.0;
		try {
			ip = RequestUtil.getIpAddr(request);
		} catch (Exception e2) {
			// TODO: handle exception
			ip = "无法获取登录用户Ip";
		}
		try {
			map = getControllerMethodDescription(point);
			// 登录名
			// user = SecurityUtils.getSubject().getPrincipal().toString();
			user = (User) session.getAttribute("userModel");
			if (user == null) {
				userMsg = "无法获取登录用户信息！";
			} else {
				userMsg = "用户名：" + user.getUserName() + " ,真实姓名:" + user.getRealName();
			}
		} catch (Exception ee) {
			userMsg = "无法获取登录用户信息！";
		}
		// 当前用户
		try {
			map = getControllerMethodDescription(point);
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
			Double s = Double.parseDouble(new DecimalFormat("#.##").format(start));
			Double e = Double.parseDouble(new DecimalFormat("#.##").format(end));
			t = (e - s) / 1000;

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		try {
			logForm.setAccountName(userMsg);
			logForm.setModule((String) map.get("module"));
			String methods = (String) map.get("methods");
			logForm.setMethods(methods);
			String description = (String) map.get("description");
			logForm.setDescription(description);
			logForm.setActionTime(t.toString());
			logForm.setUserIP(ip);
			logger.add(logForm);
			// *========控制台输出=========*//
			/*
			 * System.out.println("=====通知开始====="); System.out.println("请求方法:"
			 * + className + "." + methodName + "()");
			 * System.out.println("方法描述:" + map); System.out.println("请求IP:" +
			 * ip); System.out.println("=====通知结束=====");
			 */
		} catch (Exception e) {
			// 记录本地异常日志
			log.error("====通知异常====");
			log.error("异常信息:{}", e.getMessage());
		}
		return result;

	}

	@SuppressWarnings("rawtypes")
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					map.put("module", method.getAnnotation(SystemLog.class).module());
					map.put("methods", method.getAnnotation(SystemLog.class).methods());
					String de = method.getAnnotation(SystemLog.class).description();
					if (Common.isEmpty(de))
						de = "执行成功!";
					map.put("description", de);
					break;
				}
			}
		}
		return map;
	}
}
