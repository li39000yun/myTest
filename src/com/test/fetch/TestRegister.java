package com.test.fetch;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestRegister {

	private final static String FS_HOST = "http://register.php";

	public static void main(String[] args) throws Exception {
		TestRegister t = new TestRegister();
		System.out.println("begin");
		t.testRegister();
		System.out.println("end");
	}

	public void testRegister() throws Exception {
		String code = "efaa39-acb7dc-f2";
		String[] strs = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String[] cs = code.split("-");
		System.out.println(cs.length);
		List<String> list = new ArrayList<String>();
		StringBuffer sb;
		StringBuffer sb2;
		if (cs.length > 2) {
			for (String str : strs) {
				sb = new StringBuffer();
				sb.append(cs[0]);
				sb.append(str);
				sb.append(cs[1]);
				if (cs.length > 2) {
					for (String s : strs) {
						sb2 = new StringBuffer();
						sb2.append(s);
						sb2.append(cs[2]);
						list.add(sb.toString() + sb2.toString());
					}
				} else {
					list.add(sb.toString());
				}
			}
		}
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			Thread.sleep(500);// 停0.5秒
			System.out.println(i + ":" + list.get(i));
			if (fetch(list.get(i))) {
				break;
			}
		}
	}

	public boolean fetch(String reginvcode) throws Exception {
		boolean isRight = false;
		HttpClient client = null;
		HttpEntity entity = null;
		try {
			client = new DefaultHttpClient();// 取得httpClient默认的httpClient
			HttpPost httpPost = new HttpPost(FS_HOST);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("action", "reginvcodeck"));
			nvps.add(new BasicNameValuePair("reginvcode", reginvcode));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "GBK"));
			// response相当于你的所返回结合的一个集合，包括返回的一切信息
			HttpResponse response = client.execute(httpPost);
			entity = response.getEntity();// 得到结果的主要内容
			// 解析数据
			String html = EntityUtils.toString(entity, "GBK");
			String wrong = "<script language=\"JavaScript1.2\">parent.retmsg_invcode('1');</script>";
			if (wrong.equals(html)) {
				System.out.println(reginvcode + " 错误!");
			} else {
				isRight = true;
				System.out.println(reginvcode + " 正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entity != null) {
				entity.consumeContent();
			}
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return isRight;
	}

}
