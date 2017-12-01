package com.unitrust.timestamp3A.controller.consume.log;

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
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;
import com.unitrust.timestamp3A.service.consume.ConsumeService;

/**
 * 客户操作日志
 * 
 * @author tsa04
 *
 */
@Controller
@RequestMapping(value = "/consume/log")
public class CusConsumeLogController {

	@Autowired
	private ConsumeService consumeService;

	@RequestMapping(value = "person/list")
	@SystemLog(module = "个人用户操作日志", methods = "前往个人用户操作日志信息")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "cci/log";
	}

	@ResponseBody
	@RequestMapping("person/query")
	@SystemLog(module = "个人用户操作日志", methods = "分页查看个人用户操作日志信息")
	public Map<String, Object> query(HttpServletRequest request, CusConsumeLogVO ccl) {
		Page<CusConsumeLog> page = new Page<CusConsumeLog>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(ccl);
		page.setSearchCondition(paramMap);
		List<CusConsumeLogVO> list = consumeService.queryCusConsumeLog(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}
	
	@RequestMapping(value = "enterprise/list")
	@SystemLog(module = "企业用户操作日志", methods = "前往企业用户操作日志信息")
	public String listAll_Enterprise(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "cci/enterpriselog";
	}
	
	@ResponseBody
	@RequestMapping("enterprise/query")
	@SystemLog(module = "企业用户操作日志", methods = "分页查看个人用户操作日志信息")
	public Map<String, Object> query_Enterprise(HttpServletRequest request,  CusConsumeLogVO ccl) {
		Page<CusConsumeLog> page = new Page<CusConsumeLog>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(ccl);
		page.setSearchCondition(paramMap);
		List<CusConsumeLogVO> list = consumeService.queryEnterpriseCusConsumeLog(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}
}
