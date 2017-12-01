package com.unitrust.timestamp3A.lock;

import org.junit.Test;

public class LockTest {
	static class Outputer {
		Integer result = 0;

		public void output(String name) {
			synchronized (this) {
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}

		}
		
		public void output(Integer value,String threadName) {
			synchronized (this) {
				result = result+value;
				System.out.print(threadName+":"+result);
				System.out.println();
			}

		}
	}

	public static void main(String[] args) {
		final Outputer output = new Outputer();
		// 线程1打印zhangsan
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				//	output.output("zhangsan");
					output.output(1,"Thread1,线程加1");
				}
			}
		}).start();

		// 线程2打印lingsi
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			//		output.output("lingsi");
					output.output(-1,"Thread2,线程加-1");
				}
			}
		}).start();

		// 线程3打印wangwu
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					output.output("lingsi");
					output.output(2,"Thread3,线程加2");
				}
			}
		}).start();
	}

}
