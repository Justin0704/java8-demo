package com.example.demo.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.example.demo.constants.ThreadPoolConstants;


public class ThreadPoolExecutorDemo {

	public static void main(String[] args) {
		
		//使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				ThreadPoolConstants.CORE_POOL_SIZE, 
				ThreadPoolConstants.MAX_POOL_SIZE, 
				ThreadPoolConstants.KEEP_ALIVE_TIME,
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(ThreadPoolConstants.QUEUE_CAPACITY),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		
		List<Future<String>> futureList = new ArrayList<>();
		Callable<String> callable = new MyCallable();
		
		for(int i = 0;i < 10 ; i++){
			//提交任务到线程池
			Future<String> future = executor.submit(callable);
			//将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
			futureList.add(future);
		}
		
		for(Future<String> future : futureList){
			try {
				System.out.println(new Date() + " " + future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}
