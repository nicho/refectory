package com.chinarewards.gwt.elt.client.enterprise.model;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author yanrui 
 * @version 创建时间：2012-2-8 下午12:35:51 
 * 类说明 
 */
/**
 * @author Administrator
 * 
 */
public class PeriodType {
	public static Map<String, String> map = new HashMap<String, String>();

	static {	
		map.put("2", "二年");
		map.put("0.5", "半年");
		map.put("1", "一年");
	}

}
