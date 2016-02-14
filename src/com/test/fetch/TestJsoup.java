package com.test.fetch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestJsoup {
	public static void main(String[] args) throws IOException {
		String url = "http://www.xctg.com.cn/business/WS/default.asp";
		Response response = Jsoup.connect(url).execute();
		Map<String,String> cookies = null;
		cookies = response.cookies();
		System.out.println(cookies);
		
		String checkUrl = "http://www.xctg.com.cn/business/WS/Check_login.asp";
		Map<String,String> data = new HashMap<String, String>();
		data.put("btnEnter", "登录");
		data.put("checkbox", "Y");
		data.put("pwd", "654321");
		data.put("username", "xmhyt");
		Document doc = null;
		response = Jsoup.connect(checkUrl).cookies(cookies).timeout(30000).data(data).method(Method.POST).execute();
		doc = response.parse();
		cookies = response.cookies();
		System.out.println(cookies);
		
		String indexUrl = "http://www.xctg.com.cn/business/index.asp";
		doc = Jsoup.connect(indexUrl).cookies(cookies).timeout(30000).get();
		System.out.println(doc.select(".bottom_c").text());
		
//		System.out.println("http://query.customs.gov.cn/"+doc.getElementById("verifyIdentityImage").child(0).attr("src"));
//		VIEWSTATE = doc.getElementById("__VIEWSTATE").val();
//		System.out.println(VIEWSTATE);
//		EVENTVALIDATION = doc.getElementById("__EVENTVALIDATION").val();
//		System.out.println(EVENTVALIDATION);
	}
	
}