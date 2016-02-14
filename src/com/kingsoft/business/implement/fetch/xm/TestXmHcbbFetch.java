package com.kingsoft.business.implement.fetch.xm;

import com.kingsoft.business.vo.fetch.xm.Hcbb;

public class TestXmHcbbFetch {
	
	public static void main(String[] args) throws Exception {
		System.out.println("TestBegin");
		XmHcbbFetch fetch = new XmHcbbFetch();
		Hcbb hcbb = new Hcbb();
		hcbb.setPasswork("8675360");
		hcbb.setUserId("14601");
		String rvalue = fetch.add(hcbb);
		System.out.println(rvalue);
		System.out.println("TestEnd");
	}

}
