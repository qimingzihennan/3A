package com.unitrust.timestamp3A.controller.business;

import java.io.IOException;
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
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.business.Business;
import com.unitrust.timestamp3A.model.business.BusinessExtendField;
import com.unitrust.timestamp3A.model.business.BusinessVO;
import com.unitrust.timestamp3A.model.user.User;
import com.unitrust.timestamp3A.service.business.BusinessService;

@Controller
// @RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	/**
	 * 业务信息页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "business/business";
	}

	/**
	 * 查询业务信息数据（翻页）
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Map<String, Object> query(HttpServletRequest request, Business business) {
		Page<Business> page = new Page<Business>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 1;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(business);
		page.setSearchCondition(paramMap);
		List<Business> list = businessService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}

	/**
	 * 前往业务信息扩展字段展示页面
	 * 
	 * @param id
	 * @param model
	 * @returnlist
	 */
	@RequestMapping("/gotoFindFields")
	public String gotoFindFields(String id, Model model, Business business) {
		model.addAttribute("iframeId", id);
		List<BusinessExtendField> list = businessService.listBusinessExtendField(business.getBkey());
		model.addAttribute("list", list);
		return "business/fields";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public ResultBean remove(String Bkey) {
		ResultBean result = new ResultBean();
		result.putData("msg", "删除成功");
		try {
			businessService.remove(Bkey);
		} catch (Exception e) {
			result.putData("msg", "删除失败");
		}
		return result;
	}

	@RequestMapping("/queryTest")
	public Map<String, Object> queryTest(HttpServletRequest request) {
		Business business = new Business();
		Page<Business> page = new Page<Business>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(business);
		page.setSearchCondition(paramMap);
		/* List<Business> list = businessService.query(page); */
		List<BusinessVO> list = businessService.queryTest(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		for (int i = 0; i < list.size(); i++) {
			BusinessVO b = list.get(i);
			
			List<BusinessExtendField> bef = b.getBusinessExtendFieldList();
			for (BusinessExtendField e : bef) {
				
			}
		}
		return rb;
	}
}
