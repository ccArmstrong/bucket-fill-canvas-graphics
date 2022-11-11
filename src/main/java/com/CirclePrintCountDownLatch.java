package com;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class CirclePrintCountDownLatch {

	public static void main(String[] args) {
		HashMap<String, CountDownLatch> hashMap = new HashMap<>();
		CountDownLatch latch1 = new CountDownLatch(1);
		CountDownLatch latch2 = new CountDownLatch(1);
		CountDownLatch latch3 = new CountDownLatch(1);
		hashMap.put("1", latch1);
		hashMap.put("2", latch2);
		hashMap.put("3", latch3);
		latch1.countDown();
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						hashMap.get("1").await();
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(1);
					hashMap.put("1", new CountDownLatch(1));
					hashMap.get("2").countDown();
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						hashMap.get("2").await();
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("2");
					hashMap.put("2", new CountDownLatch(1));
					hashMap.get("3").countDown();
				}
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						hashMap.get("3").await();
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("3");
					hashMap.put("3", new CountDownLatch(1));
					hashMap.get("1").countDown();
				}
			}
		}.start();
		
	}

}
