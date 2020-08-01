package com.example.demo.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * {justin222=name, jack=nickName, justi@126.com=email}
	email
 * @author lijian
 *
 */

public class BiMapDemo {

	public static void main(String[] args) {
		
		BiMap<String,String> biMap = HashBiMap.create();
		biMap.put("name", "justin");
		biMap.put("nickName", "jack");//value重复会报错
		biMap.put("name", "justin222");//重复的key，后面的会覆盖掉前面的value值
		biMap.put("email", "justi@126.com");
		
		System.out.println(biMap.inverse());
		System.out.println(biMap.inverse().get("justi@126.com"));
		
	}
}
