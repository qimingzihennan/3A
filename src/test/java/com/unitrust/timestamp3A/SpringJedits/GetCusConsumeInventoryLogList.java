package com.unitrust.timestamp3A.SpringJedits;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.dao.impl.CusConsumeInventoryJeditsSpringDaoImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class GetCusConsumeInventoryLogList {
	private static ApplicationContext app;

	public static void main(String[] args) {
		app = new ClassPathXmlApplicationContext("/spring-redis.xml","/spring-beans.xml","spring-mybatis.xml"
				,"springmvc-servlet.xml");
		
		JeditsSpringDao cusConsumeInventoryJeditsSpringDao = (JeditsSpringDao) app
				.getBean("cusConsumeInventoryJeditsSpringDao", CusConsumeInventoryJeditsSpringDaoImpl.class);
		JedisPool jedisPool = (JedisPool) app.getBean("jedisPool");
		Jedis jedits = jedisPool.getResource();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Integer timeout = 10;
				String result = "";
				do {
					System.out.println("获取数据，如果无数据将延迟等待"+timeout+"秒");
					result = cusConsumeInventoryJeditsSpringDao.getCusConsumeInventoryLogJSON(timeout);
					if (result != null) {
						JSONObject jo = new JSONObject(result);
						String id = jo.getString("id");
						String cusIdOrPIN = jo.getString("cusIdOrPIN");
						String operateTime = jo.getString("operateTime");
						String cusType = jo.getString("cusType");
						System.out.println("结果为：");
						System.out.println("id:" + id + " ,cusIdOrPIN:" + cusIdOrPIN + " ,operateTime:" + operateTime
								+ " ,cusType:" + cusType);
					}

				} while (result != null && !result.equals(""));

				try {
					Long index = jedits.llen("cusConsumeInventoryLogList");
					System.out.println("长度为：" + index + "，List中无数据。");
					System.out.println("线程进入休眠.");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
