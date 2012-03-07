/**
 * 
 */
package com.chinarewards.gwt.elt.client.enterprise.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * @author yanrui 
 * @version 创建时间：2012-2-8 下午12:35:51 
 * 类说明 
 */
/**
 * @author Administrator
 * 
 */
public class MoneyType {
	public static Map<String, String> map = new HashMap<String, String>();
	
	static {		
		map.put("USD", "美元");		
		map.put("RMB", "人民币");
		map.put("HKD", "港币");	
	}

	public static void main(String[] args) {
		System.out.println(map.values().size());
		System.out.println(map.get("USD"));
		for (Entry<String, String> entry : MoneyType.map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
