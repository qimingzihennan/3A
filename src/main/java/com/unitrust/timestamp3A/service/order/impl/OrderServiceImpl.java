package com.unitrust.timestamp3A.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.dao.order.OrderDao;
import com.unitrust.timestamp3A.dao.order.OrderExtendFieldDao;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.model.order.OrderExtendField;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
import com.unitrust.timestamp3A.service.order.OrderExtendFieldService;
import com.unitrust.timestamp3A.service.order.OrderService;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderExtendFieldService orderExtendFieldService;

	@Autowired
	private JeditsSpringDao cusConsumeInventoryJeditsSpringDao;
	@Autowired
	private ConsumeService consumeService;
	@Autowired
	private OrderExtendFieldDao orderExtendFieldDao;

	@Override
	public List<Order> query(Page<Order> page) {
		// TODO Auto-generated method stub
		return orderDao.query(page);
	}

	@Override
	public Order findOrderById(String id) {
		// TODO Auto-generated method stub
		return orderDao.findOrderById(id);
	}

	@Override
	public int modifyOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.modifyOrder(order);
	}

	@Override
	public int deleteStatus(Integer id) {
		// TODO Auto-generated method stub
		return orderDao.deleteStatus(id);
	}
	@Transactional
	private List  saveOrders(Order order, String[] bEFValue, String[] eName, String eValue, String[] bEFName){
		List resultList = new ArrayList();
		int result = orderDao.save(order);
		OrderExtendField oed = new OrderExtendField();
		if(bEFName != null){
			for (int a = 0; a < bEFValue.length; a++) {
				oed.setbEFValue(bEFValue[a]);
				oed.setbEFName(bEFName[a]);
				oed.seteName(eName[a]);
				oed.seteValue(eValue);
				oed.setOid(order.getId());
				orderExtendFieldService.save(oed);
			}
		}
		// 生成用户消费清单信息（DB）
		CusConsumeInventory cci = consumeService.createCusConsumeInventory(order,
				CusConsumeInventory.CusConsumeInventory_status_OK);
		String Bkey = cci.getBkey();
		Integer cusId = cci.getCusId();
		Map searchMap = new HashMap();
		searchMap.put("Bkey", Bkey);
		searchMap.put("cusId", cusId);
		searchMap.put("orderType", order.getOrderType());
		resultList.add(order);
		Boolean validateResult =  consumeService.validateCusConsumeInventoryAviableIsExist(searchMap);
		if (validateResult) {
			cci.setStatus(CusConsumeInventory.CusConsumeInventory_status_Ready);
			consumeService.saveCusConsumeInventory(cci);	//生成用户或企业待使用消费清单数据
		
		}else{
			consumeService.saveCusConsumeInventory(cci); //生成用户或企业默认消费订单
			resultList.add(cci);
		}
		
		
		return resultList;
	}

	@Override
	public int save(Order order, String[] bEFValue, String[] eName, String eValue, String[] bEFName) {
		// TODO Auto-generated method stub
		List resultList = saveOrders(order,bEFValue,eName,eValue,bEFName);
		// 生成用户消费清单信息（Redis）
		Order newOrder = (Order) resultList.get(0);
		int size = resultList.size();
		if (size > 1) {
			CusConsumeInventory cci = (CusConsumeInventory) resultList.get(1);
			CCIVO saveModel = cusConsumeInventoryJeditsSpringDao.createCCIVO(newOrder, cci);
			cusConsumeInventoryJeditsSpringDao.save(saveModel);
		}
		return 1;
	}

	@Override
	public Order lastNum() {
		// TODO Auto-generated method stub
		return orderDao.lastNum();
	}

}
