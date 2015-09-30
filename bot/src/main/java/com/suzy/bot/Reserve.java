package com.suzy.bot;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.suzy.entity.Stores;

public class Reserve {

	public static List<Stores> GetStores() {
		
		String jsonString=HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/stores.json", "");
	     

		
		Map<String, Object>  maps= JSONObject.parseObject(jsonString);
		
		
		
		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(),Stores.class);
		
		
		return listPerson;
	
	}
	
	
public static List<Stores> GetAvailability() {
		
		String jsonString=HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability.json", "");
	     

		
		Map<String, Object>  maps= JSONObject.parseObject(jsonString);
		
		
		
		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(),Stores.class);
		
		
		return listPerson;
	
	}
}
