package com.unitrust.timestamp3A.SpringJedits;

import java.util.HashMap;
import java.util.Map;

import com.unitrust.timestamp3A.Jedits.JedisUtil;

import redis.clients.jedis.Jedis;

public class CopyCusConsumeInventorySetTest {
	private static final String ipAddr = "127.0.0.1";
	private static final int port = 6379;

	private static Jedis jedits = null;

	private static Map<String, Double> map = null;

	public static void main(String[] args) {
		map = new HashMap<String, Double>();
		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						map.put("timestamp_2_person", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("timestamp_2_person运行插入Redis中");
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
			//	jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("timestamp_1_person", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("timestamp_1_person运行插入Redis中");
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
			//	jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("contract_P2332_enterprise", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("contract_P2332_enterprise运行插入Redis中");
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
		//		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("contract_P2332_enterprise", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("contract_P2332_enterprise运行插入Redis中");
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
		//		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("timestamp_3_person", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("timestamp_3_person运行插入Redis中");
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
		//		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("contract_P3322222_enterprise", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("contract_P3322222_enterprise运行插入Redis中");
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
		//		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("contract_P23423432_enterprise", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("contract_P23423432_enterprise运行插入Redis中");
						Thread.sleep(9000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
		//		jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("timestamp_5_person", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("timestamp_5_person 运行插入Redis中");
						Thread.sleep(12000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
	//			jedits = JedisUtil.getInstance().getJedis(ipAddr, port);
				while (true) {
					try {
						map.put("contract_P2342343234_enterprise", Double.valueOf(System.currentTimeMillis()));
						System.out.println(jedits.zadd("cusConsumeInventorySet", map));
						System.out.println("contract_P2342343234_enterprise 运行插入Redis中");
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}
}
