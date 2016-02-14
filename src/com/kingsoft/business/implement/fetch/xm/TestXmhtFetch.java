package com.kingsoft.business.implement.fetch.xm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.kingsoft.business.implement.fetch.AbstractFetch;
import com.kingsoft.business.vo.fetch.FetchContainer;
import com.kingsoft.business.vo.fetch.FetchSearch;

public class TestXmhtFetch extends AbstractFetch {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
		TestXmhtFetch t = new TestXmhtFetch();
		// t.getzhineng();
		t.getchuchang();
	}

	private void getchuchang() throws Exception {
		// XMOSC159508
//		XmhtFetch t = new XmhtFetch();
//		List<FetchContainer> list = t.crawlBookingNo("XMOSC159508");
//		for (FetchContainer data : list) {
//			System.out.println(data.getContainerNo());
//		}
//		String html = t.crawlHtmlByBookingNo("0266502138");
//		String html = t.crawlHtmlByBookingNo("SNL6XMPL000370");
//		System.out.println(html.indexOf("在场情况") > -1);
//		if (html.indexOf("在场情况") > -1) {
//			System.out.println("在场");
//		} else {
//			System.out.println("不在场");
//		}
		
		XmhtfyFetch f = new XmhtfyFetch();
		FetchSearch search = new FetchSearch();
		search.setBookingNo("SNL6XMPL000370");
		search.setContainerNo("SNBU8180525");
//		search.setBookingNo("0266502138");
//		search.setContainerNo("WHLU5697590");
		System.out.println(f.preExecute(search));
	}

	private void getzhineng() throws Exception {
		try {
			// 访问，获取cookies
			String url = "http://www.xctg.com.cn/business/WS/default.asp";
			String html = executeGetCommonClient(url, "UTF-8");
			Header[] hs = httpReturn.getHead();
			for (Header h : hs) {
				if (h.getName().equals("Set-Cookie")) {
					cookies = h.getValue();
				}
			}
			System.out.println(cookies);
			System.out.println("--------------------");
			String checkUrl = "http://www.xctg.com.cn/business/WS/Check_login.asp";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("btnEnter", "登录"));
			nvps.add(new BasicNameValuePair("checkbox", "Y"));
			nvps.add(new BasicNameValuePair("pwd", "654321"));
			nvps.add(new BasicNameValuePair("username", "xmhyt"));
			html = executeCommonClient(checkUrl, "GBK");
			System.out.println(html);

			String indexUrl = "http://www.xctg.com.cn/business/index.asp";
			html = executeGetCommonClient(indexUrl, "UTF-8");

			String yuyueUrl = "http://www.xctg.com.cn/business/WS/new_yuyueList.Asp";
			html = executeGetCommonClient(yuyueUrl, "GBK");
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shutdownClient();// 关闭链接
		}
	}

	@Override
	public String preExecute(FetchSearch search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
