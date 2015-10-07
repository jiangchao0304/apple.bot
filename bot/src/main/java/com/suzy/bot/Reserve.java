package com.suzy.bot;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.suzy.entity.PageResult;
import com.suzy.entity.Stores;

import org.htmlparser.*;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.TextExtractingVisitor;
public class Reserve {

	public static List<Stores> GetStores() throws IOException {
		
		String jsonString=HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/stores.json", "").getPageHtml();
	     

		
		Map<String, Object>  maps= JSONObject.parseObject(jsonString);
		
		
		
		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(),Stores.class);
		
		
		return listPerson;
	
	}
	
	
public static List<Stores> GetAvailability() throws IOException{
		
		String jsonString=HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability.json", "").getPageHtml();
	     

		
		Map<String, Object>  maps= JSONObject.parseObject(jsonString);
		
		
		
		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(),Stores.class);
		
		
		return listPerson;
	
	}
 

   public static void Open(String url) throws IOException {
	
	   
	  String firstHtml=  HttpUtil.sendGet(url,"").getPageHtml();
	  
	  

	 String productUrl= JSONObject.parseObject(firstHtml).getJSONObject("body").getJSONObject("response").getJSONObject("summarySection").getJSONObject("ireserve")
	 .getJSONObject("link").getString("url");
	  
	 System.out.print(productUrl);
	   
   }
   
   public static PageResult SelectStore(String url) throws IOException {
		
	   
		  return HttpUtil.sendGet(url,"");
		  
		 
		   
	   }

}
