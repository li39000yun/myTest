package com.test.fetch;

import java.net.URLEncoder;

import com.kingsoft.control.Console;
import com.test.common.MD5Security;

public class GmGpsUtil {

	public static void main(String[] args) throws Exception {
		System.out.println("忠记联合:"+GmGpsUtil.getGpsUrl("zjlh","654321","粤BBH973"));
		System.out.println("东东物流:"+GmGpsUtil.getGpsUrl("szddwl","654321","粤BBC856"));
	}
	
	public static String getGpsUrl(String loginId, String password, String truck) throws Exception {
		String nowTime = Console.FS_TIME.getNow();
		String key = loginId + MD5Security.md5(password).toLowerCase() + nowTime;
		key = MD5Security.md5To16(key).toLowerCase();
		StringBuffer url = new StringBuffer();
		url.append("http://182.254.209.180:8034/?account=").append(encodeUTF8(loginId));
		url.append("&time=").append(encodeUTF8(nowTime).replaceAll("%3A", ":"));
		url.append("&key=").append(encodeUTF8(key));
		url.append("&carMarks=").append(encodeUTF8(truck));
		return url.toString();
	}

	private static String encodeUTF8(String code) throws Exception {
		return URLEncoder.encode(code, "UTF-8").replace("+", "%20");
	}
	
}
