package com.unitrust.timestamp3A.controller.logs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;
import com.unitrust.timestamp3A.model.logs.LogForm;
import com.unitrust.timestamp3A.service.logs.LogsService;

/**
 * 客户日志管理
 * 
 * @author tsa04
 *
 */
@Controller
@RequestMapping("/logs")
public class LogsController {

	@Autowired
	private LogsService logsService;

	@RequestMapping(value = "list")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "logs/list";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "客户操作日志", methods = "分页查看客户操作日志信息")
	public Map<String, Object> query(HttpServletRequest request, LogForm logForm) {
		Page<LogForm> page = new Page<LogForm>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(logForm);
		page.setSearchCondition(paramMap);
		List<LogForm> list = logsService.queryLogs(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}
}
