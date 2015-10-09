package com.suzy.bot;

import java.io.IOException;




import javax.swing.tree.VariableHeightLayoutCache;

import org.htmlparser.util.ParserException;

import com.suzy.entity.PageResult;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserException, IOException
    {
    	String url="http://www.apple.com/hk-zh/shop/updateSummary?node=home/shop_iphone/family/iphone6s&step=select&option.dimensionScreensize=4_7inch&option.dimensionColor=silver&option.dimensionCapacity=64gb&option.carrierModel=UNLOCKED%2FWW&carrierPolicyType=UNLOCKED&product=MKQP2ZP%2FA";
    	
    	String url2="https://reserve-hk.apple.com/HK/zh_HK/reserve/iPhone?partNumber=MKQJ2ZP%2FA&channel=1&returnURL=http%3A%2F%2Fwww.apple.com%2Fhk-zh%2Fshop%2Fbuy-iphone%2Fiphone6s%2F4.7-%E5%90%8B%E8%9E%A2%E5%B9%95-16gb-%E5%A4%AA%E7%A9%BA%E7%81%B0&sourceID=&iPP=N&appleCare=N&carrier=&store=R485";
    	
    	
    	
    	String html= HttpUtil.sendGet(url2,true);
    	
    	String html2= HttpUtil.sendGet(html,true);
    	
    	String html3= HttpUtil.sendGet(html2,true);
    	
    	System.out.println(html3);
       
    	//if(result.getRedirectUrl()!=null && result.getRedirectUrl().length()>0)
    	//{
    	//	PageResult ssPageResult=Reserve.SelectStore(result.getRedirectUrl());
    	//}
    	
    	
    }
}
