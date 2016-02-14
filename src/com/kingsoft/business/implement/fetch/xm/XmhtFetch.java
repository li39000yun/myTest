package com.kingsoft.business.implement.fetch.xm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;

import com.kingsoft.business.implement.fetch.AbstractFetch;
import com.kingsoft.business.vo.fetch.FetchContainer;
import com.kingsoft.business.vo.fetch.FetchData;
import com.kingsoft.business.vo.fetch.FetchSearch;
import com.kingsoft.common.Const;
import com.kingsoft.control.util.StringManage;
import com.kingsoft.dao.entity.baseinfo.customs.Anchor;

/**
 * 厦门海天码头数据抓取服务类
 * 
 * @author liyunqiang
 * 
 * @version 2015-2-25
 * 
 * @since JDK 1.6
 * 
 */
public class XmhtFetch extends AbstractFetch {
	private static final long serialVersionUID = 1L;
	/** 柜号查询网址 **/
	private static final String FS_URL = "http://www.xhct.com.cn/query/querycontno2004.asp";
	/** 提单号查询网址 **/
	private static final String FS_URL_BOOKING_NO = "http://www.xctg.com.cn/business/HT/new_bl.asp";

	@Override
	public String preExecute(FetchSearch search) throws Exception {
		// 设置查询参数
		nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("contnoo", search.getContainerNo()));
		return parseHtml(execute(FS_URL, "GBK"));
	}

	public String parseHtml(String html) throws Exception {
		// 创建网页解析对象
		Parser parser = new Parser(html);
		parser.setEncoding("GBK");

		// 设置过滤参数
		List<FetchData> fetchDatas = new ArrayList<FetchData>();
		NodeFilter tFilter = new TagNameFilter("table");
		NodeFilter aFilter = new HasAttributeFilter("bordercolorlight", "#CCCCCC");
		AndFilter andFilter = new AndFilter();
		andFilter.setPredicates(new NodeFilter[] { tFilter, aFilter });
		Node[] titles = parser.parse(andFilter).toNodeArray();// 过滤得符合过滤要求的节点
		Node[] names;
		Node[] values;
		if (titles != null && titles.length > 1) {
			for (int i = 0; i < titles.length; i++) {
				names = titles[i].getChildren().elementAt(0).getChildren().toNodeArray();
				values = titles[i].getChildren().elementAt(1).getChildren().toNodeArray();
				for (int j = 0; j < values.length; j = j + 1) {
					FetchData fetchData = new FetchData();
					fetchData.setName(toPlainTextString(names[j]));
					fetchData.setValue(toPlainTextString(values[j]));
					fetchDatas.add(fetchData);
				}
			}
		}
		return arrayToJson(fetchDatas.toArray(new FetchData[0]));
	}
	
	public String crawlHtmlByBookingNo(String bookingNo) throws Exception {
		nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("blnoo", bookingNo));
		nvps.add(new BasicNameValuePair("submit", "È·¶¨"));
		return execute(FS_URL_BOOKING_NO, "GBK");
	}
	
	public List<FetchContainer> crawlBookingNo(String bookingNo) throws Exception {
		nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("blnoo", bookingNo));
		nvps.add(new BasicNameValuePair("submit", "È·¶¨"));
		return parser(execute(FS_URL_BOOKING_NO, "GBK"), bookingNo);
	}

	public List<FetchContainer> parser(String html, String bookingNo) throws Exception {
		List<FetchContainer> list = new ArrayList<FetchContainer>();
		Parser parser = new Parser(html);
		parser.setEncoding("GBK");// 设置编码
		// 设置过滤参数
		NodeFilter tFilter = new TagNameFilter("td");
		NodeFilter aFilter = new HasAttributeFilter("align", "center");
		AndFilter andFilter = new AndFilter();
		andFilter.setPredicates(new NodeFilter[] { tFilter, aFilter });
		Node[] titles = parser.parse(andFilter).toNodeArray();// 过滤得符合过滤要求的节点
		String value;
		HashMap<String, Integer> maps = new HashMap<String, Integer>();
		FetchContainer data;
		if (titles != null && titles.length > 0) {
			int length = titles.length;
			for (int i = 0; i < length; i++) {
				value = toPlainTextString(titles[i]);
				if (value.equals(bookingNo)) {
					if (length > i + 7) {
						data = new FetchContainer();
						// 柜号
						data.setContainerNo(toPlainTextString(titles[i + 1]));
						if (data.getContainerNo().length() != 11) {
							continue;
						}
						if (maps.containsKey(data.getContainerNo())) {// 过滤重复的柜号
							continue;
						} else {
							maps.put(data.getContainerNo(), 0);
						}
						// 箱型
						data.setContainerType(toPlainTextString(titles[i + 2]));
						// 铅封号
						data.setSealNo(toPlainTextString(titles[i + 4]));
						// 进场时间
						data.setEnterTime(formatTime(toPlainTextString(titles[i + 6])));
						// 船名航次
						String ship = toPlainTextString(titles[i + 7]);
						if (ship.lastIndexOf("/") > -1) {
							data.setShip(ship.substring(0, ship.lastIndexOf("/")));
							data.setVoyage(ship.substring(ship.lastIndexOf("/") + 1));
						}
						list.add(data);
					}
				}
			}
		}
		return list;
	}

	// 格式化时间格式
	private String formatTime(String str) {
		if (!StringManage.isEmpty(str)) {
			if (str.indexOf("/") > -1) {
				str = str.substring(str.indexOf("/") + 1);
			}
			if (str.length() == 16) {
				str = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10) + " " + str.substring(11, 16) + ":00";
			} else if (str.length() == 10) {
				str = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10) + " 12:00:00";
			}
		}
		return str;
	}

	public List<Anchor> crawlCq() throws Exception {
		String url = "http://www.xctg.com.cn/business/HT/new_inyard_vessel.asp";
		String html = executeGet(url, "GBK");
		return parser(html);
	}

	private List<Anchor> parser(String html) throws Exception {
		html = html.replace("</TD><TR height='15px'>", "</TD></TR><TR height='15px'>");
		List<Anchor> list = new ArrayList<Anchor>();
		Parser parser = new Parser(html);
		parser.setEncoding("GBK");// 设置编码
		// 设置过滤参数
		NodeFilter tFilter = new TagNameFilter("TR");
		NodeFilter aFilter = new HasAttributeFilter("height", "15px");
		AndFilter andFilter = new AndFilter();
		andFilter.setPredicates(new NodeFilter[] { tFilter, aFilter });
		Node[] titles = parser.parse(andFilter).toNodeArray();// 过滤得符合过滤要求的节点
		Anchor data;
		Node[] nodes;
		if (titles != null && titles.length > 0) {
			for (int j = 0; j < titles.length; j++) {
				if (titles[j].getChildren() != null && titles[j].getChildren().size() > 0) {
					nodes = titles[j].getChildren().toNodeArray();
					data = new Anchor();
					data.setNameEn(toPlainTextString(nodes[0]));
					data.setNameCn(toPlainTextString(nodes[1]));
					data.setVoyageOut(toPlainTextString(nodes[2]));
					data.setPullTime(formatAnchorTime(toPlainTextString(nodes[5])));
					data.setHarborTimeStart(formatAnchorTime(toPlainTextString(nodes[6])));
					data.setHarborTimeEnd(formatAnchorTime(toPlainTextString(nodes[7])));
					data.setShoveOffTime(formatAnchorTime(toPlainTextString(nodes[11])));
					data.setDock("海天");
					data.setRegionId(Const.FS_XM_REGION_ID);
					list.add(data);
				}
			}
		}
		return list;
	}

	// 格式化时间格式
	private String formatAnchorTime(String str) {
		if (!StringManage.isEmpty(str)) {
			if (str.length() == 16) {
				str = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10) + " " + str.substring(11, 16) + ":00";
			} else if (str.length() == 10) {
				str = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10) + " 12:00:00";
			}
		}
		return str;
	}
	
}
