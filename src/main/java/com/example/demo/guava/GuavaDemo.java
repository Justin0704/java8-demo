package com.example.demo.guava;


import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class GuavaDemo {
	//连接器
	private static final Joiner joiner = Joiner.on(",").skipNulls();
	//分割器
	private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
	
	private static final CharMatcher charMatcherDigit = CharMatcher.javaDigit();
	
	
	public static void main(String[] args) {
		
		//把集合数组中的元素连接到一块
		String join = joiner.join(Lists.newArrayList("a",null,"b"));
		System.out.println("join = " + join);
		
		for(String temp : splitter.split("a,,b, ,c")){
			System.out.println(temp);
		}
		//保留数字
		System.out.println(charMatcherDigit.retainFrom("abc123!@#efg"));
		//除去数字
		System.out.println(charMatcherDigit.removeFrom("today is first day of 123 life"));
		//把指定的字符串用*号代替
		System.out.println(CharMatcher.inRange('a', 'g').or(CharMatcher.is('n')).replaceFrom("zhangsan", "*"));
		
		//快速完成到集合的转换
		List<Integer> list = Ints.asList(1,2,3,4,5,6,7);
		System.out.println(Ints.join(",",1,2,3,4,5,6,7));
		
		//原生类型数据合并
		int[] newArray = Ints.concat(new int[]{1,3,4},new int[]{4,8,6});
		System.out.println("两个数组合并后的长度 = " + newArray.length);
		//获取最大值，最小值
		System.out.println("最大值: " + Ints.max(newArray) + " ,最小值: " + Ints.min(newArray));
		//是否包含某个数字
		System.out.println(Ints.contains(newArray, 8));
		
	}
}
