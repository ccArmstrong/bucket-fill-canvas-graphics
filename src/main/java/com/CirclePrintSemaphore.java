package com;

import java.util.concurrent.Semaphore;

public class CirclePrintSemaphore {

	public static void main(String[] args) {
		Semaphore A = new Semaphore(1);
		Semaphore B = new Semaphore(0);
		Semaphore C = new Semaphore(0);
		
		new ThreadDemo(A, B, "A").start();
		new ThreadDemo(B, C, "B").start();
		new ThreadDemo(C, A, "C").start();
	}

	static class ThreadDemo extends Thread {
		private Semaphore current;
		private Semaphore next;
		private String name;
		
		public ThreadDemo(Semaphore current, Semaphore next, String name) {
			this.current = current;
			this.next = next;
			this.name = name;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					current.acquire();
					System.out.print(name);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				next.release();
			}
		}
	}
	
}
