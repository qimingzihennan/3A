package com.unitrust.timestamp3A.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.model.order.OrderExtendField;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;

@Repository(value = "orderExtendFieldDao")
public interface OrderExtendFieldDao {
	/**
	 * 新增订单扩展字段
	 * @param comboExtendFieId
	 * @return
	 */
	public int save(OrderExtendField oed);

	/**
	 * 获取订单扩展字段信息
	 * @param orderId
	 * @return
	 */
	public List<OrderExtVO> queryOrderExtVOListByOrderId(Integer orderId);
}
