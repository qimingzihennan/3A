package com.unitrust.timestamp3A.controller.order;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.unitrust.timestamp3A.common.CreateOrderNumber;
import com.unitrust.timestamp3A.common.SerialNumber;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.model.business.Business;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.combo.ComboExtendField;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.model.order.OrderExtendField;
import com.unitrust.timestamp3A.model.user.User;
import com.unitrust.timestamp3A.service.business.BusinessService;
import com.unitrust.timestamp3A.service.order.OrderExtendFieldService;
import com.unitrust.timestamp3A.service.order.OrderService;


/**
 * 订单管理
 * 
 * @author tsa04
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BusinessService businessService;
	@Autowired
	private CreateOrderNumber createOrderNumber;
	
	@RequestMapping(value = "list")
	@SystemLog(module = "订单管理", methods = "前往订单查看页面")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "order/order";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "订单管理", methods = "分页查看订单信息")
	public Map<String, Object> query(HttpServletRequest request, Order order) {
		Page<Order> page = new Page<Order>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(order);
		page.setSearchCondition(paramMap);
		List<Order> list = orderService.query(page);
		Map<String, Object> rb = new HashMap<String, Object>();
		rb.put("total", page.getTotalRecords());
		rb.put("rows", list);
		return rb;
	}
	@RequestMapping("/toAddOrder")
	@SystemLog(module = "订单管理", methods = "前往订单添加页面")
	public String toAdd(String id, Model model,Integer customerId) {
		model.addAttribute("iframeId", id);
		model.addAttribute("customerId", customerId);	
		return "order/addOrder";
	}
	@RequestMapping("/toModify")
	@SystemLog(module = "订单管理", methods = "前往订单编辑页面")
	public String findOrder(String id, String iframeId, Model model) {
		Order order = orderService.findOrderById(id);
		model.addAttribute("iframeId", iframeId);
		model.addAttribute("order", order);
		return "order/addOrder";
	}

	@ResponseBody
	@RequestMapping("/modify")
	@SystemLog(module = "订单管理", methods = "修改订单信息", description = "order")
	public Map<String, Object> modify(Order order) {
		Map<String, Object> result = new HashMap<String, Object>();
		int num = orderService.modifyOrder(order);
		if (num == 1) {
			result.put("msg", "修改成功");
		} else {
			result.put("msg", "修改失败");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteStatus")
	@SystemLog(module = "订单管理", methods = "取消订单信息", description = "order")
	public ResultBean remove(Integer id) {
		ResultBean result = new ResultBean();
		try {
			orderService.deleteStatus(id);
			result.putData("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.putData("msg", "删除失败");
		}
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody	
	@SystemLog(module = "订单管理", methods = "添加订单信息")
	public Map<String, String> save(Order order,String[] bEFValue,String[] eName,String[] bEFName,String eValue,Integer cusId){
		Map<String, String> map = new HashMap<String, String>();
		OrderExtendField oed = new OrderExtendField();
		Business business = businessService.findById(order.getBkey());
		Order orders = orderService.lastNum();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String uid_end = "";
		String numbers = "";
		int num = 0;
		int unber=15;
		try {
				numbers = createOrderNumber.create(order.getBkey());
				order.setOrderNO(numbers);
				order.setCusId(cusId);
				order.setBusinessName(business.getBusinessName());;
				if(order.getEndTime() == null){
					order.setEndTime(dateFormat.parse(dateFormat.format(date)));
				}
				if(order.getStartTime() == null){
					order.setStartTime(dateFormat.parse(dateFormat.format(date)));
				}
				order.setPayTime(dateFormat.parse(dateFormat.format(date)));
				num =  orderService.save(order,bEFValue,eName,eValue,bEFName);
				
					if(num == 1){
						map.put("msg", "添加成功");
					}else{
						map.put("msg", "添加失败");
					}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "添加失败");
		}
		return map;
	}

}
