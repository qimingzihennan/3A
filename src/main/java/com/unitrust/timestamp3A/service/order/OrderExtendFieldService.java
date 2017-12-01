package com.unitrust.timestamp3A.service.order;

import com.unitrust.timestamp3A.model.order.OrderExtendField;

public interface OrderExtendFieldService {
	/**
	 * 新增订单扩展字段
	 * @param comboExtendFieId
	 * @return
	 */
	public int save(OrderExtendField oed);
}
