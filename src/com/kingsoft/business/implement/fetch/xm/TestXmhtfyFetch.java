package com.kingsoft.business.implement.fetch.xm;

import com.kingsoft.business.vo.fetch.FetchSearch;


public class TestXmhtfyFetch {

	public static void main(String[] args) throws Exception {
		System.out.println("TestBegin");
		XmhtfyFetch xmhtfyFetch = new XmhtfyFetch();
		FetchSearch search = new FetchSearch();
		search.setBookingNo("0265530385");
		search.setContainerNo("WHLU4209350");
//		search.setContainerNo("TCLU3676656");
		String rvalue = xmhtfyFetch.preExecute(search);
		System.out.println(rvalue);
		System.out.println("TestEnd");
	}

}
