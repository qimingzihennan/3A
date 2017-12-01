package com.unitrust.timestamp3A.SpringJedits;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unitrust.timestamp3A.Jedits.JedisUtil;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.dao.impl.CusConsumeInventoryJeditsSpringDaoImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class GetCusConsumeInventorySetTest {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("/spring-redis.xml");
		JeditsSpringDao cusConsumeInventoryJeditsSpringDao = (JeditsSpringDao) app
				.getBean("cusConsumeInventoryJeditsSpringDao", CusConsumeInventoryJeditsSpringDaoImpl.class);
		/*
		 * JedisPool jedisPool = (JedisPool) app.getBean("jedisPool"); Jedis
		 * jedits = jedisPool.getResource();
		 */

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						List<Map<String, String>> result = cusConsumeInventoryJeditsSpringDao
								.getCopyCusConsumeInventory();
						System.out.println("开始处理数据");
						for (int i = 0; i < result.size(); i++) {
							Map<String, String> rs = result.get(i);
							System.out.println("第" + i + "条被更改的Redis数据");
							System.out.println(rs);
						}
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

	}
}
