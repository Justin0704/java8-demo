package com.example.demo.guava;

import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


/**
 * 	张三,语文,80
	张三,数学,90
	张三,英语,70
	李斯,语文,90
	李斯,数学,75
	李斯,英语,85
	[张三, 李斯]
	[语文, 数学, 英语]
	{语文=80, 数学=90, 英语=70}
	{张三=80, 李斯=90}
 * @author lijian
 *
 */

public class TableDemo {

	public static void main(String[] args) {
		
		Table<String, String, Integer> table = HashBasedTable.create();
		//类似redis中的hash存储
		table.put("张三", "语文", 80);
		table.put("张三", "数学", 90);
		table.put("张三", "英语", 70);
		
		table.put("李斯", "语文", 90);
		table.put("李斯", "数学", 75);
		table.put("李斯", "英语", 85);
		
		//最小单位cell
		Set<Table.Cell<String, String, Integer>> set = table.cellSet();
		for(Table.Cell<String, String, Integer> cell : set){
			System.out.println(cell.getRowKey() + "," + cell.getColumnKey() + "," + cell.getValue());
		}
		
		Set<String> rowSet = table.rowKeySet();
		System.out.println(rowSet);
		
		Set<String> columnSet = table.columnKeySet();
		System.out.println(columnSet);
		
		//根据rowKey获取信息，Map<column,value>
		System.out.println(table.row("张三"));
		
		//根据column获取信息，Map<row,value
		System.out.println(table.column("语文"));
	}
}
