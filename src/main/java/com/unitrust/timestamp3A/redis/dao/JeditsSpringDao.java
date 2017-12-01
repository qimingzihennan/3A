package com.unitrust.timestamp3A.redis.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.redis.model.CCIVO;

public interface JeditsSpringDao {

	/**
	 * 生成消费清单信息
	 * 
	 * @param saveModel
	 */
	public void save(CCIVO saveModel);

	/**
	 * 修改次数
	 * 
	 * @param editModel
	 * @return
	 */
	public boolean editNum(CCIVO editModel);

	public void delete(CCIVO deleteModel);

	/**
	 * 获取生成消费清单信息 byCCIVO
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> get(CCIVO getModel);

	/**
	 * 获取生成消费清单信息 bykey
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> get(String key);

	/**
	 * 通过操作Resit sort Set 获取消费清单信息
	 * 
	 * @return
	 */
	public List<Map<String, String>> getCopyCusConsumeInventory();

	/**
	 * 获取消费清单信息花费日志
	 * 
	 * @param timeout
	 *            获取List信息等待时间
	 * @return
	 */
	public String getCusConsumeInventoryLogJSON(Integer timeout);

	/**
	 * 通过Order,CusConsumeInventory 订单生成CCIVO
	 * 
	 * @param order
	 * @return
	 * @throws ParseException
	 */
	public CCIVO createCCIVO(Order order, CusConsumeInventory cci);

	/**
	 * 修改redis订单使用情况状态
	 * @param cci
	 */
	public void switchStatus(CusConsumeInventory cci);

}
