package com.unitrust.timestamp3A.SpringJedits;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.dao.impl.CusConsumeInventoryJeditsSpringDaoImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SetCusConsumeInventoryLogList {

	private static ApplicationContext app;

	public static void main(String[] args) {
		app = new ClassPathXmlApplicationContext("/spring-redis.xml","/spring-beans.xml","spring-mybatis.xml"
				,"springmvc-servlet.xml");
		
		JeditsSpringDao cusConsumeInventoryJeditsSpringDao = (JeditsSpringDao) app
				.getBean("cusConsumeInventoryJeditsSpringDao", CusConsumeInventoryJeditsSpringDaoImpl.class);
		JedisPool jedisPool = (JedisPool) app.getBean("jedisPool");
		Jedis jedits = jedisPool.getResource();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		new Thread(new Runnable() {
			@Override
			public void run() {
				Integer id = 20;
				while (true) {
					JSONObject jo = new JSONObject();
					jo.put("id", id.toString());
					jo.put("cusIdOrPIN", "20");
					jo.put("operateTime", sdf.format(new Date()));
					jo.put("cusType", "person");
					try {
						System.out.println(jo.toString());
						System.out.println("写入数据为：" + jedits.lpush("cusConsumeInventoryLogList", jo.toString()));
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}

}
