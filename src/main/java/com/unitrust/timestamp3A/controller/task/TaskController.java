package com.unitrust.timestamp3A.controller.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitrust.timestamp3A.authorization.SystemLog;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.model.personal.Personal;
import com.unitrust.timestamp3A.model.register.Register;
import com.unitrust.timestamp3A.model.task.TaskJob;
import com.unitrust.timestamp3A.service.excuteLog.ExcuteLogService;
import com.unitrust.timestamp3A.service.task.TaskJobService;

@Controller
@RequestMapping("/task")
public class TaskController {
	@Resource
	private TaskJobService taskJobService;
	
	@Resource
	private ExcuteLogService elog;
	
	
	@RequestMapping(value = "list")
	@SystemLog(module = "定时器管理", methods = "前往定时器管理页面")
	public String listAll(String id,Model model){
		model.addAttribute("iframeId", id);
		return "task/list";
	}
	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "定时器管理", methods = "查询定时器信息")
	public Map<String, Object> query(HttpServletRequest request,TaskJob taskJob){
		Page<TaskJob> page = new Page<TaskJob>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(taskJob);
		page.setSearchCondition(paramMap);
		List<TaskJob> list = taskJobService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
	@RequestMapping("/findTaskJobById")
	@SystemLog(module = "定时器管理", methods = "前往查看异常页面")
	public String getTaskById(Integer jobId, String id, Model model) {
		TaskJob taskjob = taskJobService.findTaskJobById(jobId);
		model.addAttribute("iframeId", id);
		model.addAttribute("taskJob", taskjob);
		return "task/taskjob";
	}
	
	@ResponseBody
	@RequestMapping("/taskById")
	public Map<String, Object> queryEnterprisePersons(HttpServletRequest request,Integer jobId){
		Page<TaskJob> page = new Page<TaskJob>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		TaskJob taskjob = taskJobService.findTaskJobById(jobId);
		Map paramMap = Common.ObjectToMap(taskjob);
		page.setSearchCondition(paramMap);
		List<ExcuteLog> list = elog.getTaskById(jobId);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	} 
}
