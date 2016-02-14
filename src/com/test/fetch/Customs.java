package com.test.fetch;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Customs {
	private static String VIEWSTATE = "";
	private static String EVENTVALIDATION = "";
	private static Document doc;
	public static void main(String[] args) throws IOException {
		doc = Jsoup.connect("http://query.customs.gov.cn/HYW2007DataQuery/FormStatusQuery.aspx").get();
		System.out.println("http://query.customs.gov.cn/"+doc.getElementById("verifyIdentityImage").child(0).attr("src"));
		VIEWSTATE = doc.getElementById("__VIEWSTATE").val();
		System.out.println(VIEWSTATE);
		EVENTVALIDATION = doc.getElementById("__EVENTVALIDATION").val();
		System.out.println(EVENTVALIDATION);
		Scanner s = new Scanner(System.in);
		String code = s.nextLine();
		
		String[] customsNos = new String[]{"520120150515464688","520120150515456927","520120150515456928","520120150515999999"};
		
		for (String no : customsNos) {
			doc = Jsoup.connect("http://query.customs.gov.cn/HYW2007DataQuery/FormStatusQuery.aspx")
					.data("__VIEWSTATE",VIEWSTATE)
					.data("__EVENTVALIDATION",EVENTVALIDATION)
					.data("submitBtn","查询")
					.data("txtVerifyNumber",code)
					.data("txtDeclareFormNo",no)
					.post();
			if(doc.getElementById("lblResult")!=null)
				System.out.println(doc.getElementById("lblResult").html());
			else
				System.out.println(no + " : 查不到数据!!");
		}
		
//		t(doc,VIEWSTATE,EVENTVALIDATION, code);
	}
	
	public static void t(Document doc,String VIEWSTATE,String EVENTVALIDATION,String code) throws IOException{
		System.out.println();
		System.out.println("520120150515464688");
		doc = Jsoup.connect("http://query.customs.gov.cn/HYW2007DataQuery/FormStatusQuery.aspx")
				.data("__VIEWSTATE",VIEWSTATE)
				.data("__EVENTVALIDATION",EVENTVALIDATION)
				.data("submitBtn","查询")
				.data("txtVerifyNumber",code)
				.data("txtDeclareFormNo","520120150515464688")
				.post();
		System.out.println(doc.getElementById("lblResult").html());
	}
}