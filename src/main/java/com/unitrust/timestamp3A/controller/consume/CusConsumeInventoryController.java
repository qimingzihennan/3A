package com.unitrust.timestamp3A.controller.consume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.service.enterprise.EnterpriseService;
import com.unitrust.timestamp3A.vo.CusConsumeInventoryModel;
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
	@Autowired
	private EnterpriseService enterpriseService;

	@RequestMapping(value = "list")
	@SystemLog(module = "用户订单消费管理", methods = "前往用户订单消费管理查看页面")
	public String listAll(String id, Model model) {
		model.addAttribute("iframeId", id);
		return "cci/list";
	}

	@ResponseBody
	@RequestMapping("/query")
	@SystemLog(module = "用户订单消费管理", methods = "分页查看用户订单消费信息")
	public Map<String, Object> query(HttpServletRequest request, CusConsumeInventory cci,String mobile) {
		Page<CusConsumeInventory> page = new Page<CusConsumeInventory>();
		Integer pageNum = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 1;
		Integer rows = request.getParameter("rows") != null ? Integer.valueOf(request.getParameter("rows")) : 10;
		page.setPageNum(pageNum);
		page.setPageSize(rows);
		Map paramMap = Common.ObjectToMap(cci);
		paramMap.put("mobile",mobile);
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

	@ResponseBody
	@RequestMapping("/queryRedisData")
	@SystemLog(module = "用户订单消费管理", methods = "查看Redis数据")
	public Map<String, Object> queryRedisData(Integer cusId,String orderType,String bkey){
		Map<String, Object> rb = new HashMap<String, Object>();
		StringBuilder redis_key =new StringBuilder(bkey);
		CCIVO ccivo = new CCIVO();
		ccivo.setKey(bkey);
		if (CusConsumeInventory.CusConsumeInventory_orderType_enterprise.equals(orderType)){
			PIN_SD pin_sd = enterpriseService.getPSByEnterpriseId(cusId);
			String pin = pin_sd.getPIN();
			redis_key.append("_").append(pin).append("_enterprise");
			ccivo.setCusIdOrPIN(pin);
			ccivo.setType("enterprise");
		}else{
			redis_key.append("_").append(cusId).append("_person");
			ccivo.setCusIdOrPIN(cusId.toString());
			ccivo.setType("person");
		}
		CusConsumeInventoryModel cusConsumeInventoryModel= consumeService.queryRedisData(ccivo);
		rb.put("cusConsumeInventoryModel",cusConsumeInventoryModel);
		return rb;
	}
}
