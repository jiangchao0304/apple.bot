package com.suzy.bot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.suzy.entity.PageResult;

public class HttpUtil {


	private static CookieManager manager = new CookieManager();

	
	public static String sendGet(String url )throws IOException 
	{
		return sendGet(url,false);
	}

	public static String sendGet(String url ,boolean getLocation) throws IOException {

		CookieHandler.setDefault(manager);
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		
		try {
			
			SSLContext sslcontext = SSLContext.getInstance("TLS"); 
			sslcontext.init(null, new TrustManager[]{myX509TrustManager}, new java.security.SecureRandom());
			

			String urlNameString = url;

			URL realUrl = new URL(urlNameString);
			
			HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
			
			connection.setSSLSocketFactory(sslcontext.getSocketFactory());
			connection.setHostnameVerifier(
			new HostnameVerifier()
            {

                public boolean verify(String hostname, SSLSession session)
                {
                   return true;
                }
             });
			
			CookieHandler.setDefault(manager);
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();

			CookieStore cookieJar = manager.getCookieStore();
			List<HttpCookie> cookies = cookieJar.getCookies();
			for (HttpCookie cookie : cookies) {
				System.out.println(cookie);
			}

			// 定义 BufferedReader输入流来读取URL的响应
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			String location = connection.getHeaderField("Location");
			System.out.println("302 Found！" + location);
			if(getLocation && location!=null && location.length()>0)
				return location;
				 
	
			
			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}
		}

		return resultBuffer.toString();
	}

	public static String sendPost(String url, String params) throws IOException {

		
		CookieHandler.setDefault(manager);

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			String urlNameString = url;

			URL realUrl = new URL(urlNameString);

			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			out.writeBytes(params);

			out.flush();

			out.close();

			CookieStore cookieJar = manager.getCookieStore();
			List<HttpCookie> cookies = cookieJar.getCookies();
			for (HttpCookie cookie : cookies) {
				System.out.println(cookie);
			}

			inputStream = connection.getInputStream();

			inputStreamReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}
		}

		return resultBuffer.toString();
	}

	private static TrustManager myX509TrustManager = new X509TrustManager() { 

	    public X509Certificate[] getAcceptedIssuers() { 
	        //return null; 
	    	return new java.security.cert.X509Certificate[0];
	    } 

	 
	    public void checkServerTrusted(X509Certificate[] chain, String authType) 
	    throws CertificateException { 
	    } 

	   
	    public void checkClientTrusted(X509Certificate[] chain, String authType) 
	    throws CertificateException { 
	    } 
	};
	
	
	
}
