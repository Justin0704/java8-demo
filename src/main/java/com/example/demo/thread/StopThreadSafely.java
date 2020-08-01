package com.example.demo.thread;

public class StopThreadSafely implements Runnable{

	private volatile boolean stop = false;
	
	int m = 0;
	
	@Override
	public void run() {
		
		while(!stop){
			synchronized ("") {
				m ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				m --;
				String name = Thread.currentThread().getName();
				System.out.println(name + " ,m = " + m);
			}
		}
	}
	
	public void terminate(){
		stop = true;
	}
	
	
	public static void main(String[] args) {
		
		StopThreadSafely t = new StopThreadSafely();
		Thread t1 = new Thread(t);
		t1.start();
		
		for(int i = 0;i<10;i++){
			new Thread(t).start();
		}
		t.terminate();
		
	}
}
