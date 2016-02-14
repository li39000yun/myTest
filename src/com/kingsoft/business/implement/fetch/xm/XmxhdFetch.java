package com.kingsoft.business.implement.fetch.xm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.kingsoft.business.implement.fetch.AbstractFetch;
import com.kingsoft.business.vo.fetch.FetchContainer;
import com.kingsoft.business.vo.fetch.FetchData;
import com.kingsoft.business.vo.fetch.FetchSearch;
import com.kingsoft.control.Console;
import com.kingsoft.control.util.StringManage;
import com.kingsoft.dao.entity.baseinfo.customs.Anchor;

/**
 * 厦门新海达码头数据抓取服务类
 * 
 * @author liyunqiang
 * 
 * @version 2015-2-28
 * 
 * @since JDK 1.6
 * 
 */
public class XmxhdFetch extends AbstractFetch {
	private static Logger S_Logger = Logger.getLogger(AbstractFetch.class);
	private static final long serialVersionUID = 1L;

	@Override
	public String preExecute(FetchSearch search) throws Exception {
		// 设置查询参数
		String html = StringManage.FS_EMPTY;
		try {
			if (S_Logger.isDebugEnabled()) {
				S_Logger.debug("fetch - [com.kingsoft.business.implement.fetch.xm.XmxhdFetch] fetch ");
			}

			// 访问网站获取cookie
			String url = "http://www.xhdct.com";
			executeGetCommonClient(url);

			// 登录网站
			url = "http://www.xhdct.com:8081/handler/validate.ashx";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("Action", "Login"));
			nvps.add(new BasicNameValuePair("password", "123"));
			nvps.add(new BasicNameValuePair("username", "guest"));
			executeCommonClient(url, "GBK");
			
			// 查询数据
			url = "http://www.xhdct.com:8081/handler/query.ashx?view=XhdContainerRecordQuery&queryType=";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("columns", "*"));
			nvps.add(new BasicNameValuePair("orginwhere", "{}"));
			nvps.add(new BasicNameValuePair("page", "1"));
			nvps.add(new BasicNameValuePair("pagesize", "100"));
			nvps.add(new BasicNameValuePair("sortname", "进港时间"));
			nvps.add(new BasicNameValuePair("sortorder", "desc"));
			nvps.add(new BasicNameValuePair("where", "{\"groups\":[{},{\"op\":\"and\",\"rules\":[{\"op\":\"like\",\"field\":\"a.cntr\",\"value\":\"" + search.getContainerNo() + "\",\"type\":\"string\"}]}],\"op\":\"and\"}"));
			html = executeCommonClient(url);
		} catch (Exception e) {
			if (S_Logger.isDebugEnabled()) {
				S_Logger.debug("fetch - [com.kingsoft.business.implement.fetch.xm.XmxhdFetch] fetch error " + e.getMessage());
			}
		} finally {
			shutdownClient();
		}
		return parseHtml(html);
	}

	public String parseHtml(String html) throws Exception {
		List<FetchData> fetchDatas = new ArrayList<FetchData>();
		JSONObject json = JSONObject.fromObject(html);
		JSONArray cons = json.getJSONArray("Rows");
		if (cons.size() > 0) {
			JSONObject c = (JSONObject) cons.get(0);
			Iterator<?> it = c.keys();
			String key;
			String value;
			while (it.hasNext()) {
				key = (String) it.next();
				value = c.getString(key);
				FetchData fetchData = new FetchData();
				fetchData.setName(key);
				fetchData.setValue(value);
				fetchDatas.add(fetchData);
			}
		}
		return arrayToJson(fetchDatas.toArray(new FetchData[0]));
	}

	public List<FetchContainer> crawlBookingNo(String bookingNo) throws Exception {
		List<FetchContainer> list = new ArrayList<FetchContainer>();
		try {
			// 登录网站
			String url = "http://www.xhdct.com:8081/handler/validate.ashx";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("Action", "Login"));
			nvps.add(new BasicNameValuePair("password", "123"));
			nvps.add(new BasicNameValuePair("username", "guest"));
			executeCommonClient(url, "GBK");

			// 查询数据
			url = "http://www.xhdct.com:8081/handler/query.ashx?view=XhdContainerRecordQuery&queryType=";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("columns", "*"));
			nvps.add(new BasicNameValuePair("orginwhere", "{}"));
			nvps.add(new BasicNameValuePair("page", "1"));
			nvps.add(new BasicNameValuePair("pagesize", "1000"));
			nvps.add(new BasicNameValuePair("sortname", "进港时间"));
			nvps.add(new BasicNameValuePair("sortorder", "desc"));
			nvps.add(new BasicNameValuePair("where", "{\"groups\":[{},{\"op\":\"and\",\"rules\":[{\"op\":\"like\",\"field\":\"a.bill_no\",\"value\":\"" + bookingNo + "\",\"type\":\"string\"}]}],\"op\":\"and\"}"));
			list =  parser(executeCommonClient(url));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shutdownClient();
		}
		return list;
	}

	private List<FetchContainer> parser(String html) {
		List<FetchContainer> list = new ArrayList<FetchContainer>();
		JSONObject json = JSONObject.fromObject(html);
		if (json.containsKey("Rows")) {
			JSONArray cons = json.getJSONArray("Rows");
			if (cons.size() > 0) {
				FetchContainer data;
				JSONObject j;
				int length = cons.size();
				for (int i = 0; i < length; i++) {
					j = (JSONObject) cons.get(i);
					data = new FetchContainer();
					data.setContainerNo(j.getString("箱号"));
					data.setBookingNo(j.getString("提单号"));
					data.setContainerType(j.getString("尺寸") + j.getString("类型"));
					data.setSealNo(j.getString("铅封"));
					data.setWeight(j.getString("毛重"));
					data.setLine(j.getString("箱公司"));
					data.setShip(j.getString("船名"));
					data.setVoyage(j.getString("航次"));
					data.setEnterTime(j.getString("进港时间"));
					list.add(data);
				}
			}
		}
		return list;
	}
	public List<Anchor> crawlCq() throws Exception {
		List<Anchor> list = new ArrayList<Anchor>();
		try {
			String url = "http://www.xhdct.com";
			executeGetCommonClient(url);

			// 登录网站
			url = "http://www.xhdct.com:8081/handler/validate.ashx";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("Action", "Login"));
			nvps.add(new BasicNameValuePair("password", "123"));
			nvps.add(new BasicNameValuePair("username", "guest"));
			executeCommonClient(url, "GBK");

			// 查询数据
			url = "http://www.xhdct.com:8081/handler/query.ashx?view=XhdForecastQuery&queryType=";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("columns", "*"));
			nvps.add(new BasicNameValuePair("orginwhere", "{}"));
			nvps.add(new BasicNameValuePair("page", "1"));
			nvps.add(new BasicNameValuePair("pagesize", "1000"));
			nvps.add(new BasicNameValuePair("sortname", "日期"));
			nvps.add(new BasicNameValuePair("sortorder", "asc"));
			String beginDate = Console.FS_DATE.add(Console.FS_DATE.getNow(), -1);
			String endDate = Console.FS_DATE.add(beginDate, 8);
			nvps.add(new BasicNameValuePair("where", "{\"groups\":[{},{\"op\":\"and\",\"rules\":[{\"op\":\"greaterorequal\",\"field\":\"TRUNC(b.eta)\",\"value\":\"" + beginDate + "T16:00:00.000Z\",\"type\":\"date\"},{\"op\":\"lessorequal\",\"field\":\"TRUNC(b.eta)\",\"value\":\"" + endDate + "T16:00:00.000Z\",\"type\":\"date\"}]}],\"op\":\"and\"}"));
			String html = executeCommonClient(url);

			list = parserAnchor(html);
		} finally {
			shutdownClient();
		}
		return list;
	}

	private List<Anchor> parserAnchor(String html) {
		List<Anchor> list = new ArrayList<Anchor>();
		JSONObject json = JSONObject.fromObject(html);
		JSONArray datas = json.getJSONArray("Rows");
		if (datas.size() > 0) {
			Anchor data;
			JSONObject j;
			int length = datas.size();
			for (int i = 0; i < length; i++) {
				j = datas.getJSONObject(i);
				data = new Anchor();
				data.setNameCn(j.getString("船名"));
				data.setNameEn(j.getString("船名"));
				data.setVoyageOut(j.getString("航次"));
				data.setPullTime(formatTime(j.getString("实际靠泊")));
				data.setHarborTimeStart(formatTime(j.getString("开闸时间")));
				data.setHarborTimeEnd(formatTime(j.getString("截箱时间")));
				data.setDock("新海达");

				list.add(data);
			}
		}
		return list;
	}

	public static String formatTime(String str) {
		if (!StringManage.isEmpty(str)) {
			if (str.equals("null")) {
				return StringManage.FS_EMPTY;
			}
			if (str.length() == 11) {
				str = Console.FS_DATE.getNow().substring(0, 5) + str + ":00";
			}
		}
		return str;
	}
	
}
