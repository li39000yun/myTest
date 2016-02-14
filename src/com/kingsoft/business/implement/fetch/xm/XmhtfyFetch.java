package com.kingsoft.business.implement.fetch.xm;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 厦门海天码头费用数据抓取服务类
 * 
 * @author liyunqiang
 * 
 * @version 2015-2-25
 * 
 * @since JDK 1.6
 * 
 */
public class XmhtfyFetch extends AbstractFetch {
	private static final long serialVersionUID = 1L;
	/** 查询提单号网址 **/
	private static final String FS_URL = "http://www.xhct.com.cn/business/HT/new_fee.asp?p1=tdh&submit=%C3%88%C2%B7%C2%B6%C2%A8&contno=";

	/** 查询柜号网址 **/
	private static final String FS_URL_CON = "http://www.xctg.com.cn/business/HT/new_fee_n.asp?contno=";

	public String preExecute(FetchSearch search) throws Exception {
		List<FetchData> datas = parseHtml(executeGet(FS_URL + search.getBookingNo(), "GBK"), search.getContainerNo());
		if (datas.size() == 0 && isExist(search)) {
			return parseHtml(executeGet(FS_URL_CON + search.getContainerNo(), "GBK"));
		}
		return arrayToJson(datas.toArray(new FetchData[0]));
	}

	/**
	 * 查询此柜是否匹配提单号 用提单号去查询，在结果里匹配柜号
	 * 
	 * @param search
	 *            查询对象
	 * @return
	 * @throws Exception
	 */
	private boolean isExist(FetchSearch search) throws Exception {
		boolean isExist = false;
		XmhtFetch xmhtFetch = new XmhtFetch();
		String html = xmhtFetch.crawlHtmlByBookingNo(search.getBookingNo());
		if (html.indexOf("在场情况") == -1) {// 不在场的才去获取费用
			List<FetchContainer> datas = xmhtFetch.parser(html, search.getBookingNo());
			if (datas != null && datas.size() > 0) {
				for (FetchContainer data : datas) {
					if (data.getContainerNo().equals(search.getContainerNo())) {
						isExist = true;
						break;
					}
				}
			}
		}
		return isExist;
	}

	public List<FetchData> parseHtml(String html, String containerNo) throws Exception {
		Node[] titles = getTitles(html);
		Node[] nodes;
		Node[] childNodes;
		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		if (titles != null && titles.length > 0) {
			if (titles[0].getChildren() != null && titles[0].getChildren().size() >= 2) {
				nodes = titles[0].getChildren().toNodeArray();
				// 费用名称
				childNodes = nodes[0].getChildren().toNodeArray();
				for (int i = 0; i < childNodes.length; i++) {
					names.add(toPlainTextString(childNodes[i]));
				}
				for (int len = 1; len <= nodes.length; len++) {
					childNodes = nodes[len].getChildren().toNodeArray();
					// 校验柜号
					if (containerNo.equals(toPlainTextString(childNodes[0]))) {
						// 费用金额
						for (int i = 0; i < childNodes.length; i++) {
							values.add(toPlainTextString(childNodes[i]));
						}
						break;
					}
				}
			}
		}
		List<FetchData> fetchDatas = getFetchDatas(names, values);
		return fetchDatas;
	}

	private Node[] getTitles(String html) throws Exception {
		Parser parser = new Parser(html);
		parser.setEncoding("GBK");// 设置编码
		// 设置过滤参数
		NodeFilter tFilter = new TagNameFilter("table");
		NodeFilter aFilter = new HasAttributeFilter("bordercolorlight", "#808080");
		AndFilter andFilter = new AndFilter();
		andFilter.setPredicates(new NodeFilter[] { tFilter, aFilter });
		return parser.parse(andFilter).toNodeArray();// 过滤得符合过滤要求的节点
	}

	public String parseHtml(String html) throws Exception {
		Node[] titles = getTitles(html);
		Node[] nodes;
		Node[] childNodes;
		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		if (titles != null && titles.length > 0) {
			if (titles[0].getChildren() != null && titles[0].getChildren().size() == 2) {
				nodes = titles[0].getChildren().toNodeArray();
				// 费用名称
				childNodes = nodes[0].getChildren().toNodeArray();
				for (int i = 0; i < childNodes.length; i++) {
					names.add(toPlainTextString(childNodes[i]));
				}
				// 费用金额
				childNodes = nodes[1].getChildren().toNodeArray();
				for (int i = 0; i < childNodes.length; i++) {
					values.add(toPlainTextString(childNodes[i]));
				}
			}
		}
		List<FetchData> fetchDatas = getFetchDatas(names, values);
		return arrayToJson(fetchDatas.toArray(new FetchData[0]));
	}

	private List<FetchData> getFetchDatas(List<String> names, List<String> values) {
		List<FetchData> fetchDatas = new ArrayList<FetchData>();
		if (names.size() == values.size()) {
			for (int i = 0; i < names.size(); i++) {
				if (isNumeric(values.get(i))) {
					if (Double.parseDouble(values.get(i)) != 0) {
						FetchData fetchData = new FetchData();
						fetchData.setName(names.get(i));
						fetchData.setValue(values.get(i));
						fetchDatas.add(fetchData);
					}
				}
			}
		}
		return fetchDatas;
	}

}
