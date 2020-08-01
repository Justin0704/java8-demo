package com.example.demo.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{
	
	Semaphore m = new Semaphore(5);
	
	@Override
	public void run() {
		
		for(int i = 0 ;i < 100 ;i++){
			test();
		}
	}
	
	private void test(){
		try {
			m.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is coming...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " is out...");
		
		m.release();
	}
	
	public static void main(String[] args) {
		
		SemaphoreDemo t = new SemaphoreDemo();
		Thread t1 = new Thread(t);
		t1.setName("Semaphore例子线程");
		t1.start();
	}
}
