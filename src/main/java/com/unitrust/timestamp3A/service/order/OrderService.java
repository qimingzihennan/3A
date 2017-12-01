package com.unitrust.timestamp3A.service.order;

import java.util.List;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.order.Order;

public interface OrderService {

	public List<Order> query(Page<Order> page);

	public Order findOrderById(String id);

	public int modifyOrder(Order order);

	public int deleteStatus(Integer id);
	
	public Order lastNum();

	public int save(Order order, String[] bEFValue, String[] eName, String eValue, String[] bEFName);

}
