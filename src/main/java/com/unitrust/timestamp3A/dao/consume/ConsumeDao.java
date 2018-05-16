package com.unitrust.timestamp3A.dao.consume;

import java.util.List;
import java.util.Map;

import com.unitrust.timestamp3A.vo.CusConsumeInventoryModel;
import org.springframework.stereotype.Repository;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;

@Repository(value = "consumeDao")
public interface ConsumeDao {

	public List<CusConsumeInventoryVO> queryCusConsumeInventory(Page<CusConsumeInventory> page);

	public void switchStatus(Map queryMap);

	public List<CusConsumeLogVO> queryCusConsumeLog(Page<CusConsumeLog> page);

	public List<CusConsumeLogVO> queryEnterpriseCusConsumeLog(Page<CusConsumeLog> page);

	public void saveCusConsumeInventory(CusConsumeInventory cci);

	public void updateCusConsumeInventory(CusConsumeInventory cci);

	public void saveCusConsumeInventoryLog(CusConsumeLog ccl);

	public CusConsumeInventory findCusConsumeInventoryById(Map queryMap);

	public CusConsumeInventory findCusConsumeInventoryAvilableByCusIdAndBkey(Map searchMap);

	public void addCusConsumeInventory(CusConsumeInventory cci);

	List<CusConsumeInventoryVO> queryEnterpriseCusConsumeInventory(Page<CusConsumeInventory> page);

	List<CusConsumeInventoryModel> findCusConsumeInventory(Map type);
}
