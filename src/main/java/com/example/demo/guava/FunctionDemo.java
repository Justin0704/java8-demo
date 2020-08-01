package com.example.demo.guava;

import java.util.Collection;
import java.util.List;


import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
/**
 * 函数式编程的好处在于在集合遍历操作中提供自定义Function的操作，比如transform转换。
 * @author lijian
 *
 */
public class FunctionDemo {

	public static void main(String[] args) {
		
		List<String> list = Lists.newArrayList("helloworld","java","php is good language");
		
		Function<String, String> f1 = new Function<String, String>() {
			@Override
			public String apply(String input) {
				return input.length() <= 5 ? input : input.substring(0,5);
			}
		};
		
		Function<String, String> f2 = new Function<String, String>() {
			@Override
			public String apply(String input) {
				return input.toUpperCase();
			}
		};
		
		Function<String, String> resultFun = Functions.compose(f1, f2);
		
		Collection<String> collection = Collections2.transform(list, resultFun);
		for(String s : collection){
			System.out.println(s);
		}
	}
}
