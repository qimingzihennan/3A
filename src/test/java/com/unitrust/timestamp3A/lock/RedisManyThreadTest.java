package com.unitrust.timestamp3A.lock;

import org.junit.BeforeClass;

import com.unitrust.timestamp3A.Jedits.JedisUtil;

import redis.clients.jedis.Jedis;

public class RedisManyThreadTest {
	private static final String ipAddr = "127.0.0.1";
	private static final int port = 6379;
	private static Jedis jedis = null;

	public static synchronized Jedis init() {
		return jedis = JedisUtil.getInstance().getJedis(ipAddr, port);
	}

	public static void close() {
		JedisUtil.getInstance().closeJedis(jedis, ipAddr, port);
	}

	static int flag = 0;
	static int de = 0;

	public static void main(String[] args) {
		Jedis connect= null;
		connect =init();
		connect.flushDB();
		connect.set("key1", "100");
		System.out.println("key1:" + jedis.get("key1"));
		close();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					Integer koufei = 3;
					 if (Integer.valueOf(result) == 0 && Integer.valueOf(result) >= koufei ) {
						return;
					}
					System.out.println( connect1.decrBy("key1",koufei));
					// close();

				}
			}
		}).start();

		// 线程2打印lingsi
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					//System.out.println( connect1.decr("key1"));
						System.out.println( connect1.decrBy("key1",5));
					// close();

				}
			}
		}).start();

		// 线程3打印wangwu
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Jedis connect1 = init();
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// output.output("zhangsan");
					
					// System.out.println("第"+flag+++"运行，"+"将key1的值加上100000："
					// + jedis.incrBy("key1", 100000));
					 String result = connect1.get("key1");
					 if (Integer.valueOf(result) == 0) {
						return;
					}
					System.out.println( connect1.decr("key1"));
					// close();

				}
			}
		}).start();
	}
}
