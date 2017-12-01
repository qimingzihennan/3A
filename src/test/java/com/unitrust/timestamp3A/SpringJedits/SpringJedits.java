package com.unitrust.timestamp3A.SpringJedits;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.unitrust.timestamp3A.common.util.DateUtil;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.dao.impl.CusConsumeInventoryJeditsSpringDaoImpl;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SpringJedits {
	private ApplicationContext app;
	private RedisTemplate<Serializable, Serializable> jedisTemplate;

	private JedisConnectionFactory jedisConnectionFactory;

	private JeditsSpringDao cusConsumeInventoryJeditsSpringDao;

	private JedisPool jedisPool;

	private Jedis jedits;

	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("/spring-redis.xml","/spring-beans.xml","spring-mybatis.xml"
				,"springmvc-servlet.xml");
		jedisTemplate = (RedisTemplate<Serializable, Serializable>) app.getBean("jedisTemplate");
		jedisConnectionFactory = (JedisConnectionFactory) app.getBean("jedisConnectionFactory");
		cusConsumeInventoryJeditsSpringDao = (JeditsSpringDao) app.getBean("cusConsumeInventoryJeditsSpringDao",
				CusConsumeInventoryJeditsSpringDaoImpl.class);
		jedisPool = (JedisPool) app.getBean("jedisPool");
		jedits = jedisPool.getResource();
	}

	@Test
	public void crud() {
		// -------------- Create ---------------
		jedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(jedisTemplate.getStringSerializer().serialize("user.uid." + 1),
						jedisTemplate.getStringSerializer().serialize("zhanghongwei"));
				return null;
			}
		});
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
		ccivo.setCusIdOrPIN("1111");
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
		jedits = jedisPool.getResource();
		Map<String, String> result = jedits.hgetAll("timestamp_5_person");
		String sdCode = result.get("sdCode");
		String id = result.get("id");
		String status = result.get("status");
		String paidMode = result.get("paidMode");
		String startTime = result.get("startTime");
		String endTime = result.get("endTime");
		String num =result.get("num");
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

	@Test
	public void sortedSetTest() {
		jedits = jedisPool.getResource();
		Map<String, Double> map = new HashMap<>();
		map.put("key2", Double.valueOf(System.currentTimeMillis()));
		map.put("key3", Double.valueOf(System.currentTimeMillis()));
		map.put("key4", Double.valueOf(System.currentTimeMillis()));
		map.put("key5", Double.valueOf(System.currentTimeMillis()));
		map.put("key6", 1.0);
		map.put("key6", 2.0);

		System.out.println(jedits.zadd("sortedSetTest", System.currentTimeMillis(), "key1"));
		System.out.println(jedits.zadd("sortedSetTest", map));
		System.out.println("sortedSetTest中的所有元素：" + jedits.zrange("sortedSetTest", 0, -1));
		System.out.println("sortedSetTest中的所有元素个数：" + jedits.zcard("sortedSetTest"));
		Set<String> values = jedits.zrange("sortedSetTest", 0, -1);

		System.out.println("删除sortedSetTest中的所有元素个数：" + jedits.zremrangeByLex("sortedSetTest", "[alpha", "[omega"));
		System.out.println("sortedSetTest中的所有元素个数：" + jedits.zcard("sortedSetTest"));
		System.out.println("sortedSetTest中的所有元素：" + jedits.zrange("sortedSetTest", 0, -1));

	}

	@Test
	public void listTest() {
		String[] abc = { "test1", "test1", "test2", "test3", "test4" };
		for (String v : abc) {
			System.out.println("插入一个元素：" + v + "  " + jedits.lpush("listTest", v));
		}
		System.out.println("listTest的内容：" + jedits.lrange("listTest", 0, -1));
		Long num = 0L;
		do {
			num = jedits.llen("listTest");
			List<String> result = jedits.brpop(10, "listTest");
			System.out.println("List第一个元素：" + result);
		} while (num != 0);
		System.out.println("List第一个元素：" + jedits.brpop(10, "listTest"));
		System.out.println("listTest的内容：" + jedits.lrange("listTest", 0, -1));
	}

	@Test
	public void getTest() {
		CCIVO ccivo = new CCIVO();
		ccivo.setKey("contract");
		ccivo.setCusIdOrPIN("P2342343234");
		ccivo.setType("enterprise");
		Map<String, String> result1 = cusConsumeInventoryJeditsSpringDao.get(ccivo);
		System.out.println("第一次结果：" + result1);
		Map<String, String> result2 = cusConsumeInventoryJeditsSpringDao.get("timestamp_1_person");
		System.out.println("第二次结果：" + result2);
		String num = result1.get("num");
		System.out.println("第一次结果剩余次数：" + num);
	}

	@Test
	public void editNumTest() {
		CCIVO ccivo = new CCIVO();
		ccivo.setKey("contract");
		ccivo.setCusIdOrPIN("P2342343234");
		ccivo.setType("enterprise");
		ccivo.setPlusOrMinus(1000);
		Map<String, String> result1 = cusConsumeInventoryJeditsSpringDao.get(ccivo);
		String num = result1.get("num");
		System.out.println("修改前的结果剩余次数：" + num);
		boolean result = cusConsumeInventoryJeditsSpringDao.editNum(ccivo);
		result1 = cusConsumeInventoryJeditsSpringDao.get(ccivo);
		num = result1.get("num");
		if (result) {
			System.out.println("修改成功！结果为："+num);
		}else{
			System.out.println("修改失敗！结果为："+num);
		}
		do{
			ccivo.setPlusOrMinus(-30000);
		    result = cusConsumeInventoryJeditsSpringDao.editNum(ccivo);
			/*String key = ccivo.getKey();
			String cusIdOrPIN = ccivo.getCusIdOrPIN();
			String type = ccivo.getType();
			String redisKey = key + "_" + cusIdOrPIN + "_" + type;
			System.out.println("修改后的结果剩余次数：" + jedits.hincrByFloat(redisKey, "num", 6));*/
		    result1 = cusConsumeInventoryJeditsSpringDao.get(ccivo);
			num = result1.get("num");
			if (result) {
				System.out.println("修改成功！结果为："+num);
			}else{
				System.out.println("修改失敗！结果为："+num);
			}
		}while(result);
		
		
	}
	
	@Test
	public void editModelTest() {
		jedits.hset("timestamp_1_person", "status","1");
	}

}
