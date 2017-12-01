package com.unitrust.timestamp3A.service.consume.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitrust.timestamp3A.Jedits.JedisUtil;
import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.DateUtil;
import com.unitrust.timestamp3A.dao.consume.ConsumeDao;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;
import com.unitrust.timestamp3A.model.enterprise.Enterprise;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
import com.unitrust.timestamp3A.service.enterprise.EnterpriseService;

@Service(value = "consumeService")
public class ConsumeServiceImpl implements ConsumeService {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeServiceImpl.class);

	@Autowired
	private ConsumeDao consumeDao;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private JeditsSpringDao cusConsumeInventoryJeditsSpringDao;

	@Override
	public List<CusConsumeInventoryVO> queryCusConsumeInventory(Page<CusConsumeInventory> page) {
		// TODO Auto-generated method stub
		return consumeDao.queryCusConsumeInventory(page);
	}

	@Override
	public void switchStatus(Map queryMap) {
		// TODO Auto-generated method stub
		consumeDao.switchStatus(queryMap);
		Integer changeStatus = (Integer) queryMap.get("changeStatus");
		CusConsumeInventory cci = consumeDao.findCusConsumeInventoryById(queryMap);
		cci.setStatus(changeStatus.toString());
		cusConsumeInventoryJeditsSpringDao.switchStatus(cci);
	}

	@Override
	public List<CusConsumeLogVO> queryCusConsumeLog(Page<CusConsumeLog> page) {
		// TODO Auto-generated method stub
		return consumeDao.queryCusConsumeLog(page);
	}

	@Override
	public List<CusConsumeLogVO> queryEnterpriseCusConsumeLog(Page<CusConsumeLog> page) {
		// TODO Auto-generated method stub
		return consumeDao.queryEnterpriseCusConsumeLog(page);
	}

	@Override
	public void saveCusConsumeInventory(CusConsumeInventory cci) {
		// TODO Auto-generated method stub
		consumeDao.saveCusConsumeInventory(cci);
	}

	@Override
	public CusConsumeInventory createCusConsumeInventory(Order order, String status) {
		// TODO Auto-generated method stub
		CusConsumeInventory cci = new CusConsumeInventory();
		cci.setBusinessName(order.getBusinessName()); // 业务模块名称
		cci.setBkey(order.getBkey()); // 业务模块key
		Integer cusId = order.getCusId();
		cci.setCusId(cusId); // 客户ID
		cci.setTotalNumber(order.getNumber()); // 总次数
		cci.setResidueNumber(order.getNumber()); // 剩余次数
		cci.setStartTime(order.getStartTime()); // 开始时间
		cci.setEndTime(order.getEndTime());// 结束时间
		cci.setPaidMode(order.getPaidMode()); // 计费模式 1次数、2天数、3次数+天数
		cci.setStatus(status); // 状态0可用、1不可用、2待使用
		cci.setOrderId(order.getId());// 订单id
		String orderType = order.getOrderType();
		cci.setOrderType(orderType); // 类型1个人2企业
		if (CusConsumeInventory.CusConsumeInventory_orderType_enterprise.equals(orderType)) {
			// Enterprise enterprise =
			// enterpriseService.getEnterpriseById(cusId);
			PIN_SD pinSD = enterpriseService.getPSByEnterpriseId(cusId);
			String PIN = pinSD.getPIN();
			String sdCode = pinSD.getSD();
			cci.setPIN(PIN);// 企业PIN码
			cci.setSdCode(sdCode);// 企业PIN码对应的sd码
		}

		return cci;
	}

	public void updateCusConsumeInventory(CusConsumeInventory cci) {
		// TODO Auto-generated method stub
		consumeDao.updateCusConsumeInventory(cci);
	}

	@Override
	public CusConsumeInventory createCusConsumeInventory(Map<String, String> cusConsumeInventoryRedis) {
		// TODO Auto-generated method stub
		CusConsumeInventory cci = new CusConsumeInventory();
		String id = cusConsumeInventoryRedis.get("id");
		cci.setId(Integer.valueOf(id));
		String status = cusConsumeInventoryRedis.get("status");
		cci.setStatus(status);
		String paidMode = cusConsumeInventoryRedis.get("paidMode");
		cci.setPaidMode(paidMode);

		String dateFormat = "yyyy-MM-dd";
		String startTime = cusConsumeInventoryRedis.get("startTime");
		Date sTime = new DateUtil(dateFormat).getDateByString(startTime);
		if (sTime != null) {
			cci.setStartTime(sTime);
		}
		String endTime = cusConsumeInventoryRedis.get("endTime");
		Date eTime = new DateUtil(dateFormat).getDateByString(endTime);
		if (eTime != null) {
			cci.setEndTime(eTime);
		}
		Double num = Double.valueOf(cusConsumeInventoryRedis.get("num"));
		cci.setResidueNumber(num.intValue());

		return cci;
	}

	@Transactional
	public void updateCusConsumeInventoryFromRedis() {
		List<Map<String, String>> result = cusConsumeInventoryJeditsSpringDao.getCopyCusConsumeInventory();
		for (Map<String, String> cusConsumeInventoryRedis : result) {
			CusConsumeInventory cci = this.createCusConsumeInventory(cusConsumeInventoryRedis);
			this.updateCusConsumeInventory(cci);
		}
	}

	@Transactional
	public void saveCusConsumeInventoryLogFromRedis() {
		Integer timeout = 5; // 获取Redis list中的数据 等待时间5s
		String result = "";
		do {
			// logger.info("获取数据，如果无数据将延迟等待" + timeout + "秒");
			result = cusConsumeInventoryJeditsSpringDao.getCusConsumeInventoryLogJSON(timeout);
			if (result != null) {
				JSONObject jo = new JSONObject(result);
				String id = jo.getString("id");
				String cusIdOrPIN = jo.getString("cusIdOrPIN");
				String operateTime = jo.getString("operateTime");
				String cusType = jo.getString("cusType");
				String dateFormat = "yyyy-MM-dd hh:mm:ss";
				Date oTime = new DateUtil(dateFormat).getDateByString(operateTime);

				CusConsumeLog ccl = new CusConsumeLog(Integer.valueOf(id), cusIdOrPIN, oTime, cusType);
				logger.info("结果为：" + "id:" + id + " ,cusIdOrPIN:" + cusIdOrPIN + " ,operateTime:" + operateTime
						+ " ,cusType:" + cusType);
				this.saveCusConsumeInventoryLog(ccl);
			}

		} while (result != null && !result.equals(""));
	}

	@Override
	public void saveCusConsumeInventoryLog(CusConsumeLog ccl) {
		// TODO Auto-generated method stub
		String cusType = ccl.getCusType();
		if (CusConsumeLog.CusConsumeLog_type_enterprise.equals(cusType)) {
			String cusIdOrPIN = ccl.getCusIdOrPIN();
			PIN_SD pin_sd = enterpriseService.findPSByPIN(cusIdOrPIN);
			Integer eterpriseid = pin_sd.getEnterpriseId();
			ccl.setCusIdOrPIN(eterpriseid.toString());
		}

		consumeDao.saveCusConsumeInventoryLog(ccl);
	}

	@Override
	public Boolean validateCusConsumeInventoryAviableIsExist(Map searchMap) {
		// TODO Auto-generated method stub
		
		CusConsumeInventory cci = consumeDao.findCusConsumeInventoryAvilableByCusIdAndBkey(searchMap);
		if (cci != null) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void addCusConsumeInventory(CusConsumeInventory cci) {
		// TODO Auto-generated method stub
		consumeDao.addCusConsumeInventory(cci);
	}
}
