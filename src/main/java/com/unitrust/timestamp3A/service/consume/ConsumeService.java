package com.unitrust.timestamp3A.service.consume;

import java.util.List;
import java.util.Map;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;
import com.unitrust.timestamp3A.model.order.Order;

public interface ConsumeService {

	/**
	 * 查询用户消费清单信息
	 * 
	 * @param page
	 * @return
	 */
	public List<CusConsumeInventoryVO> queryCusConsumeInventory(Page<CusConsumeInventory> page);

	/**
	 * 修改用户消费清单操作状态
	 * 
	 * @param queryMap
	 */
	public void switchStatus(Map queryMap);

	/**
	 * 查询（个人）用户消费清单操作日志信息
	 * 
	 * @param page
	 * @return
	 */
	public List<CusConsumeLogVO> queryCusConsumeLog(Page<CusConsumeLog> page);

	/**
	 * 查询（企业）用户消费清单操作日志信息
	 * 
	 * @param page
	 * @return
	 */
	public List<CusConsumeLogVO> queryEnterpriseCusConsumeLog(Page<CusConsumeLog> page);

	/**
	 * 保存用户消费清单信息
	 * 
	 * @param cci
	 */
	public void saveCusConsumeInventory(CusConsumeInventory cci);

	/**
	 * 形成CusConsumeInventory 实体信息
	 * 
	 * @param order
	 * @return
	 */
	public CusConsumeInventory createCusConsumeInventory(Order order, String status);

	/**
	 * 形成CusConsumeInventory 实体信息
	 * 
	 * @param order
	 * @return
	 */
	public CusConsumeInventory createCusConsumeInventory(Map<String, String> cusConsumeInventoryRedis);

	/**
	 * 更新用户消费清单数据
	 * 
	 * @param cci
	 */
	public void updateCusConsumeInventory(CusConsumeInventory cci);
	
	/**
	 * 生成CusConsumeInventoryLog  用户消费清单操作日志信息
	 */
	public void saveCusConsumeInventoryLog(CusConsumeLog ccl);
	
	/**
	 * 通过查询Redis中cusConsumeInventorySet，更新用户消费清单数据
	 */
	public void updateCusConsumeInventoryFromRedis();
	
	/**
	 * 通过查询Redis中cusConsumeInventoryLogList，生成用户消费清单操作日志信息数据
	 */
	public void saveCusConsumeInventoryLogFromRedis();

	/**
	 * 判断是否存在使用的消费清单数据
	 * @param searchMap
	 * @return
	 */
	public Boolean validateCusConsumeInventoryAviableIsExist(Map searchMap);

	/**
	 * 保存用户待使用消费清单信息
	 * @param cci
	 */
	public void addCusConsumeInventory(CusConsumeInventory cci);
}

