package com.unitrust.timestamp3A.controller.consume;

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
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.service.consume.ConsumeService;

/**
 * 用户订单消费管理
 * 
 * @author tsa04
 *
 */
@Controller
@RequestMapping(value = "/consume/cci")
public class CusConsumeInventoryController {
	@Autowired
	private ConsumeService consumeService;

	@RequestMapping(value = "list")
	@SystemLog(module = "用户订单消费管理", methods = "前往用户订单消费管理查看页面")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "cci/list";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "用户订单消费管理", methods = "分页查看用户订单消费信息")
	public Map<String, Object> query(HttpServletRequest request, CusConsumeInventory cci) {
		Page<CusConsumeInventory> page = new Page<CusConsumeInventory>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(cci);
		page.setSearchCondition(paramMap);
		List<CusConsumeInventoryVO> list = consumeService.queryCusConsumeInventory(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}

	@ResponseBody
	@RequestMapping("/switchStatus")
	@SystemLog(module = "用户订单消费管理", methods = "冻结订单")
	public ResultBean switchStatus(Integer id, Integer changeStatus) {
		ResultBean result = new ResultBean();
		result.putData("msg", "修改成功");
		Map queryMap = new HashMap();
		queryMap.put("id", id);
		queryMap.put("changeStatus", changeStatus);
		try {
			consumeService.switchStatus(queryMap);
		} catch (Exception e) {
			result.putData("msg", "修改失败");
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}
}
