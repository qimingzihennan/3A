package com.unitrust.timestamp3A.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.order.Order;

@Repository(value = "orderDao")
public interface OrderDao {

	public List<Order> query(Page<Order> page);

	public Order findOrderById(String id);

	public int modifyOrder(Order order);

	public int deleteStatus(Integer id);
	
	public int save(Order order);
	
	public Order lastNum();
}
