package com.unitrust.timestamp3A.redis.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.unitrust.timestamp3A.common.util.DateUtil;
import com.unitrust.timestamp3A.dao.order.OrderExtendFieldDao;
import com.unitrust.timestamp3A.model.combo.Combo;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;
import com.unitrust.timestamp3A.redis.template.JedisTemplateAPI;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CusConsumeInventoryJeditsSpringDaoImpl implements JeditsSpringDao {
	@Autowired
	private OrderExtendFieldDao orderExtendFieldDao;

	private JedisTemplateAPI redisClientTemplate;

	public JedisTemplateAPI getRedisClientTemplate() {
		return redisClientTemplate;
	}

	public void setRedisClientTemplate(JedisTemplateAPI redisClientTemplate) {
		this.redisClientTemplate = redisClientTemplate;
	}

	@Override
	public void save(CCIVO saveModel) {
		String key = saveModel.getKey();
		String cusIdOrPIN = saveModel.getCusIdOrPIN();
		String type = saveModel.getType();

		String redisKey = key + "_" + cusIdOrPIN + "_" + type;

		Map<String, String> redisKeyValueMap = new HashMap<String, String>();
		if (saveModel.getType() != "person") {
			redisKeyValueMap.put("sdCode", saveModel.getSdCode() == null ? "" : saveModel.getSdCode());
		}
		if (!saveModel.getContent().equals("")) {
			redisKeyValueMap.put("content", saveModel.getContent() == null ? "" : saveModel.getContent());
		}
		redisKeyValueMap.put("status", saveModel.getStatus());
		redisKeyValueMap.put("paidMode", saveModel.getPaidMode());
		redisKeyValueMap.put("startTime", saveModel.getStartTime() == null ? "" : saveModel.getStartTime());
		redisKeyValueMap.put("endTime", saveModel.getEndTime() == null ? "" : saveModel.getEndTime());
		redisKeyValueMap.put("id", saveModel.getId());
		JSONObject jo = new JSONObject();
		List<OrderExtVO> extList = saveModel.getExt();
		Object[] ob = new Object[extList.size()];
		for (int i = 0; i < extList.size(); i++) {
			OrderExtVO oevo = extList.get(i);
			Map<String, String> oevoMap = new HashMap<String, String>();
			oevoMap.put("BEFName", oevo.getBEFName());
			oevoMap.put("eName", oevo.geteName());
			oevoMap.put("eValue", oevo.geteValue());
			ob[i] = oevoMap;
		}
		jo.put("ext", ob);
		redisKeyValueMap.put("ext", jo.toString());
		redisKeyValueMap.put("num", saveModel.getNum().toString());
		redisClientTemplate.hmset(redisKey, redisKeyValueMap);
	}

	@Override
	/**
	 * 修改次数
	 */
	public boolean editNum(CCIVO editModel) {
		// TODO Auto-generated method stub
		String key = editModel.getKey();
		String cusIdOrPIN = editModel.getCusIdOrPIN();
		String type = editModel.getType();
		String redisKey = key + "_" + cusIdOrPIN + "_" + type;
		Double plusOrMinus = editModel.getPlusOrMinus();
		Double num = Double.valueOf(redisClientTemplate.hget(redisKey, "num"));
		Double result = num + plusOrMinus;
		if (result < 0) {
			return false;
		}
		// jedis.hincrBy(key, "num", result);
		redisClientTemplate.hincrByFloat(redisKey, "num", plusOrMinus);
		return true;
	}

	@Override
	public void delete(CCIVO deleteModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> get(CCIVO getModel) {
		// TODO Auto-generated method stub
		String key = getModel.getKey();
		String cusIdOrPIN = getModel.getCusIdOrPIN();
		String type = getModel.getType();
		String redisKey = key + "_" + cusIdOrPIN + "_" + type;
		return redisClientTemplate.hgetAll(redisKey);
	}

	@Override
	public Map<String, String> get(String key) {
		// TODO Auto-generated method stub\
		return redisClientTemplate.hgetAll(key);
	}

	@Override
	public List<Map<String, String>> getCopyCusConsumeInventory() {
		// TODO Auto-generated method stub
		Set<String> values = redisClientTemplate.zrange("cusConsumeInventorySet", 0L, -1L);
		// jedis.zremrangeByLex("cusConsumeInventorySet", "[alpha", "[omega");
		Long index = redisClientTemplate.zcard("cusConsumeInventorySet");
		redisClientTemplate.zremrangeByRank("cusConsumeInventorySet", 0, index - 1);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		Iterator<String> iteratror = values.iterator();
		while (iteratror.hasNext()) {
			String redisKey = iteratror.next();
			Map<String, String> resultMap = this.get(redisKey);
			resultList.add(resultMap);
		}
		return resultList;
	}

	public String getCusConsumeInventoryLogJSON(Integer timeout) {
		List<String> result = redisClientTemplate.brpop(timeout, "cusConsumeInventoryLogList");
		if (result.size() != 0) {
			return result.get(1);
		} else {
			return null;
		}
	}

	@Override
	public CCIVO createCCIVO(Order order, CusConsumeInventory cci) {
		// TODO Auto-generated method stub
		CCIVO result = new CCIVO();
		result.setContent(order.getContent());
		result.setStatus(cci.getStatus());
		result.setPaidMode(cci.getPaidMode());
		String dateFormat = "yyyy-MM-dd";
		Date startTime = cci.getStartTime();
		String sTime = startTime == null ? "" : new DateUtil(dateFormat).getDateStringByDate(startTime);
		result.setStartTime(sTime);
		Date endTime = cci.getEndTime();
		String eTime = endTime == null ? "" : new DateUtil(dateFormat).getDateStringByDate(endTime);
		result.setEndTime(eTime);
		result.setId(cci.getId().toString());
		Integer orderId = order.getId();
		List<OrderExtVO> ext = orderExtendFieldDao.queryOrderExtVOListByOrderId(orderId);
		result.setExt(ext);
		result.setKey(cci.getBkey());
		String orderType = cci.getOrderType();

		if (CusConsumeInventory.CusConsumeInventory_orderType_enterprise.equals(orderType)) {
			// 企业
			String PIN = cci.getPIN();
			String sdCode = cci.getSdCode();
			result.setCusIdOrPIN(PIN);
			result.setSdCode(sdCode);
			result.setType(CCIVO.CCIVO_type_enterprise);
		} else {
			Integer cusId = cci.getCusId();
			result.setCusIdOrPIN(cusId.toString());
			result.setType(CCIVO.CCIVO_type_person);
		}
		if (cci.getResidueNumber() == null || cci.getResidueNumber() == 0) {
			return result;
		} else {
			Double num = cci.getResidueNumber().doubleValue();
			result.setNum(num);
			return result;
		}

	}

	@Override
	public void switchStatus(CusConsumeInventory cci) {
		// TODO Auto-generated method stub
		String key = cci.getBkey();
		String orderType = cci.getOrderType();
		String type = "";
		String cusIdOrPIN = "";
		if (CusConsumeInventory.CusConsumeInventory_orderType_enterprise.equals(orderType)) {
			// 企业
			type = CCIVO.CCIVO_type_enterprise;
			cusIdOrPIN = cci.getPIN();
		} else {
			type = CCIVO.CCIVO_type_person;
			cusIdOrPIN = cci.getCusId().toString();
		}

		String redisKey = key + "_" + cusIdOrPIN + "_" + type;

		String status = cci.getStatus();
		redisClientTemplate.hset(redisKey, "status", status);

	}

}
