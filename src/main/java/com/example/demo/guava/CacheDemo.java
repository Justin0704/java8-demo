package com.example.demo.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheDemo {

	public static void main(String[] args) throws Exception{
		
		
		LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
				.maximumSize(10)//最多存放十个数据
				.expireAfterWrite(10, TimeUnit.SECONDS)//缓存10秒，10秒之后进行回收
				.recordStats()//开启，记录状态数据功能
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(String key) throws Exception {
						return -1;////数据加载，默认返回-1，也可以是查询操作，如从DB查询
					}
				});
		//只查询缓存，没有命中，即返回null
		System.out.println(cache.getIfPresent("key1"));
		//put数据，放在缓存中
		cache.put("key1", 1);
		//再次查询，已经存在缓存中
		System.out.println(cache.getIfPresent("key1"));
		
		//查询缓存，未命中，调用load方法，返回-1
		System.out.println(cache.get("key2"));
		//put数据，更新缓存
		cache.put("key2", 2);
		//查询得到最新的数据
		System.out.println(cache.get("key2"));
		//插入十个数据
		for (int i = 3; i < 13; i++) {
			cache.put("key"+i, i);
		}
		//超过最大容量，删除最早插入的数据
		System.out.println("size:" + cache.size());
		System.out.println(cache.getIfPresent("key2"));
		//等待5秒
		Thread.sleep(5000);
		cache.put("key1", 1);
		cache.put("key2", 2);
		//key5没失效，key3、key4已经失效
		System.out.println(cache.getIfPresent("key5"));
		//等待5秒
		Thread.sleep(5000);
		//此时key5-key12已经失效，但是size没有更新
        System.out.println("size :" + cache.size());
        System.out.println(cache.getIfPresent("key1"));
        System.out.println("size :" + cache.size());
      //此时只有key1，key2没有失效
        System.out.println("size :" + cache.size());
        System.out.println("status, hitCount:" + cache.stats().hitCount()
                + ", missCount:" + cache.stats().missCount());
	}
	
	
//	//定义缓存的实现
//	private static final CacheLoader<Long, User> userCacheLoader = new CacheLoader<Long, User>() {
//		@Override
//		public User load(Long aLong) throws Exception {
//			//从数据库或redis中加载数据
//			User user = new User(aLong,Thread.currentThread().getName() + "--" +
//					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "--" + aLong);
//			return user;
//		}
//	};
//	
//	//定义缓存策略，提供对外访问缓存
//	private static final LoadingCache<Long, User> userCacheData = CacheBuilder.newBuilder()
//			.expireAfterAccess(2, TimeUnit.SECONDS)
//			.expireAfterWrite(2, TimeUnit.SECONDS)
//			.refreshAfterWrite(2, TimeUnit.SECONDS)
//			.maximumSize(10000L)
//			.build(userCacheLoader);
}
