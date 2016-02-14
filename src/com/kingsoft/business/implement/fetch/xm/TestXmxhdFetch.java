package com.kingsoft.business.implement.fetch.xm;

import java.util.List;

import com.kingsoft.dao.entity.baseinfo.customs.Anchor;


public class TestXmxhdFetch {

	public static void main(String[] args) throws Exception {
		System.out.println("TestBegin");
		XmxhdFetch xmxhdFetch = new XmxhdFetch();
		List<Anchor> list = xmxhdFetch.crawlCq();
		System.out.println(list.size());
		for (Anchor a : list) {
			System.out.println(a.getNameCn()+a.getNameEn());
		}
		System.out.println("TestEnd");
	}

}
