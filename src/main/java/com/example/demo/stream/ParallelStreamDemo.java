package com.example.demo.stream;

import java.util.stream.Stream;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		
		System.out.println(100);
		
	}
	
	
	public static Long getSum(int n){
		return Stream.iterate(1L,i->i+1).limit(n).parallel().reduce(0L,Long::sum);
	}
}
