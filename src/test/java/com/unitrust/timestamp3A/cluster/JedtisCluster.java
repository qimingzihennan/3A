package com.unitrust.timestamp3A.cluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.dao.impl.CusConsumeInventoryJeditsSpringDaoImpl;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedtisCluster {
	private ApplicationContext app;
	private JedisCluster jedisClusterClient;
	private JeditsSpringDao cusConsumeInventoryJeditsSpringDao;

	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("/spring-redis-Cluster.xml", "spring-mybatis.xml",
				"springmvc-servlet.xml","/spring-beans.xml");
		jedisClusterClient = (JedisCluster) app.getBean("jedisClusterClient");
		cusConsumeInventoryJeditsSpringDao = (JeditsSpringDao) app.getBean("cusConsumeInventoryJeditsSpringDao",
				CusConsumeInventoryJeditsSpringDaoImpl.class);
	}

	@Test
	public void testAdd() {
		System.out.println("测试set，key为test3，值为这是测试," + jedisClusterClient.set("test3", "这是测试"));
		System.out.println("=========");
		System.out.println(jedisClusterClient.get("test3"));
	}

	@Test
	public void hashTest() {
		CCIVO ccivo = new CCIVO();
		ccivo.setContent("测试content");
		ccivo.setStatus("1");
		ccivo.setPaidMode("2");
		ccivo.setStartTime("2017-01-02");
		ccivo.setEndTime("2017-01-03");
		ccivo.setId("2");
		ccivo.setKey("timestamp");
		ccivo.setCusIdOrPIN("5");
		ccivo.setSdCode("df123");
		ccivo.setType("person");
		List<OrderExtVO> ext = new ArrayList();
		for (int i = 0; i < 3; i++) {
			OrderExtVO vo = new OrderExtVO();
			vo.setBEFName("12344");
			vo.seteName("123Name");
			vo.seteValue("12345612312312321");
			ext.add(vo);
		}
		ccivo.setExt(ext);
		ccivo.setNum(82218.0);
		cusConsumeInventoryJeditsSpringDao.save(ccivo);
	}

	@Test
	public void hashGetTest() {
	
		Map<String, String> result = jedisClusterClient.hgetAll("contract_49a4d435655ae3d00d65a18f4773385f_enterprise");
		String sdCode = result.get("sdCode");
		String id = result.get("id");
		String status = result.get("status");
		String paidMode = result.get("paidMode");
		String startTime = result.get("startTime");
		String endTime = result.get("endTime");
		String num = result.get("num");
		String content = result.get("content");
		String ext = result.get("ext");
		JSONObject jsonobject = new JSONObject(ext);
		JSONArray extStringJSONarray = jsonobject.getJSONArray("ext");

		JSONObject jo = new JSONObject();
		jo.put("sdCode", sdCode);
		jo.put("id", id);
		jo.put("status", status);
		jo.put("paidMode", paidMode);
		jo.put("startTime", startTime);
		jo.put("endTime", endTime);
		jo.put("num", num);
		jo.put("content", content);
		jo.put("ext", extStringJSONarray.toString());
		System.out.println(jo.toString());
	}
}
