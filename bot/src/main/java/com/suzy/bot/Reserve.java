package com.suzy.bot;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.suzy.entity.Stores;

import org.htmlparser.*;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.TextExtractingVisitor;
import java.util.HashMap;
import java.util.Map;
public class Reserve {

	public static List<Stores> GetStores() throws IOException {

		String jsonString = HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/stores.json");

		Map<String, Object> maps = JSONObject.parseObject(jsonString);

		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(), Stores.class);

		return listPerson;

	}

	public static List<Stores> GetAvailability() throws IOException {

		String jsonString = HttpUtil.sendGet("https://reserve.cdn-apple.com/HK/zh_HK/reserve/iPhone/availability.json");

		Map<String, Object> maps = JSONObject.parseObject(jsonString);

		List<Stores> listPerson = JSONObject.parseArray(maps.get("stores").toString(), Stores.class);

		return listPerson;

	}

	public static void Open(String url) throws IOException {

		String firstHtml = HttpUtil.sendGet(url);

		String productUrl = JSONObject.parseObject(firstHtml).getJSONObject("body").getJSONObject("response")
				.getJSONObject("summarySection").getJSONObject("ireserve").getJSONObject("link").getString("url");

		System.out.print(productUrl);

	}

	public static String SelectStore(String url) throws IOException {

		return HttpUtil.sendGet(url);

	}

	
	
	public static void Login() throws IOException
	{
		String sku="https://reserve-hk.apple.com/HK/zh_HK/reserve/iPhone?partNumber=MKQJ2ZP%2FA&channel=1&returnURL=http%3A%2F%2Fwww.apple.com%2Fhk-zh%2Fshop%2Fbuy-iphone%2Fiphone6s%2F4.7-%E5%90%8B%E8%9E%A2%E5%B9%95-16gb-%E5%A4%AA%E7%A9%BA%E7%81%B0&sourceID=&iPP=N&appleCare=N&carrier=&store=R485";
	   
        String step1= HttpUtil.sendGet(sku,true);
    	
        System.out.println("step1"+step1);
    	String step2= HttpUtil.sendGet(step1,true);
    	
    	System.out.println("step2"+step2);
    	//拿到登录地址
    	String step3= HttpUtil.sendGet(step2,true);
    	
    	Matcher serviceKey =Pattern.compile("serviceKey: '(.*?)',").matcher(step3);
    	serviceKey.find();
    	
    	Matcher appId =Pattern.compile("requestorAppId: '(.*?)'").matcher(step3);
    	appId.find();
    	
    	Matcher locale =Pattern.compile("locale: '(.*?)',").matcher(step3);
    	locale.find();
    	
    	
    	 
        Map<String,String> header = new HashMap<String,String>();
       
    	header.put("X-Apple-Widget-Key", serviceKey.group(1));   //.group(1));
    	header.put("X-Apple-I-FD-Client-Info", "");
    	header.put("X-Apple-App-Id", appId.group(1));
    	header.put("X-Apple-Locale", locale.group(1));
    	header.put("X-Requested-With", "XMLHttpRequest");
    	header.put("Referer", step2);
    	header.put("Content-Type","application/json; charset=UTF-8");
    	
    	
    	String ss= HttpUtil.sendPost("https://signin.apple.com/appleauth/auth/signin", "{\"accountName\":\"10000@qq.com\",\"password\":\"Jc19850304\",\"rememberMe\":false}",header);
    	
    	System.out.println(ss);
	
	}
	
}
