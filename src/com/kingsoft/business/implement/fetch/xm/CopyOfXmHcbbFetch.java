package com.kingsoft.business.implement.fetch.xm;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.kingsoft.business.implement.fetch.AbstractFetch;
import com.kingsoft.business.vo.fetch.FetchSearch;
import com.kingsoft.business.vo.fetch.xm.Hcbb;
import com.kingsoft.control.util.StringManage;

/**
 * 厦门海沧报备抓取类
 * 
 * @author liyunqiang
 * 
 * @version 2015-7-10
 * 
 * @since JDK 1.6
 * 
 */
public class CopyOfXmHcbbFetch extends AbstractFetch {
	private static final long serialVersionUID = 1L;
	public HashMap<String, String> conWeightMap = new HashMap<String, String>();// 箱型-空箱重量数组,(key=箱型;value=空箱重量)

	public String add(Hcbb hcbb) throws Exception {
		String rvalue = StringManage.FS_EMPTY;
		try {
			// 获取cookie
			String wwwUrl = "http://hcbsg.fjeport.gov.cn/IdentifyCode.ashx";
			executeGetCommonClient(wwwUrl);
			Header[] hs = httpReturn.getHead();
			for (Header h : hs) {
				if (h.getName().equals("Set-Cookie")) {
					cookies = h.getValue();
				}
			}
			System.out.println(cookies);

			encoding = "UTF-8";// 设置编码
			// 登录网站
			wwwUrl = "http://hcbsg.fjeport.gov.cn/Index.ashx?__method=login";
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("userName", hcbb.getUserId()));
			nvps.add(new BasicNameValuePair("password", hcbb.getPasswork()));
			nvps.add(new BasicNameValuePair("checkCode", "6789"));
			executeCommonClient(wwwUrl);

			// 打开主界面
			wwwUrl = "http://hcbsg.fjeport.gov.cn/MainForm.aspx";
			executeGetCommonClient(wwwUrl);
			cookies += ";__hcbs=userId:" + hcbb.getUserId() + "==corpNo:" + hcbb.getCorpNo() + ";";
			if (hcbb.getType() == 1) {
				// 打开出口报备录入界面
				wwwUrl = "http://tqbb.fjeport.gov.cn/TrailerNumberInfoAdd.aspx";
				executeGetCommonClient(wwwUrl);
				// 获取箱型数组,设置空箱重量
				setConWeightMap(hcbb);

				// 查询车牌重量信息
				setTruckInfo(hcbb);

				// 添加出口库报备资料
				wwwUrl = "http://tqbb.fjeport.gov.cn/TrailerNumberInfo.ashx?__method=c&cntrnoStatus=1";
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("containerInfo.cntrWeight", hcbb.getCntrWeight()));
				nvps.add(new BasicNameValuePair("containerInfo.cntrWeight", hcbb.getCntrWeight2()));
				nvps.add(new BasicNameValuePair("containerInfo.cntrno", hcbb.getCntrno()));
				nvps.add(new BasicNameValuePair("containerInfo.cntrno", hcbb.getCntrno2()));
				nvps.add(new BasicNameValuePair("containerInfo.eirinfoExist", "0"));
				nvps.add(new BasicNameValuePair("containerInfo.eirinfoExist", "0"));
				nvps.add(new BasicNameValuePair("containerInfo.eirno", ""));
				nvps.add(new BasicNameValuePair("containerInfo.eirno", ""));
				nvps.add(new BasicNameValuePair("containerInfo.sealno", hcbb.getSealNo()));
				nvps.add(new BasicNameValuePair("containerInfo.sealno", hcbb.getSealNo2()));
				nvps.add(new BasicNameValuePair("containerInfo.sizetypeofcntr", hcbb.getSizetypeofcntr()));
				nvps.add(new BasicNameValuePair("containerInfo.sizetypeofcntr", hcbb.getSizetypeofcntr2()));
				nvps.add(new BasicNameValuePair("dockCode", hcbb.getDockCode()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.addedFlag", "0"));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.cntrNo", hcbb.getTruckCntrNo()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.corpname", ""));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.corpno", ""));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.customsCode", "3708"));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.dockCode", hcbb.getDockCode()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.msgStatus", "1"));// 0保存，1保存并发送
				nvps.add(new BasicNameValuePair("trailerNumberInfo.remark", ""));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.rfidSerialno", hcbb.getRfidSerialno()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.sealno", hcbb.getTruckSealno()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.tfcode", hcbb.getTfcode()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.tfcode1", hcbb.getTfcode1()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.trailerFrameWeight", hcbb.getTrailerFrameWeight()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.trailerWeight", hcbb.getTrailerWeight()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.typedescription", hcbb.getTypedescription()));
				nvps.add(new BasicNameValuePair("trailerNumberInfo.vehicleno", hcbb.getVehicleno()));
				rvalue = executeCommonClient(wwwUrl);
			} else if (hcbb.getType() == 0) {
				// 打开进口报备录入界面
				wwwUrl = "http://tqbb.fjeport.gov.cn/ImportTrailerNumberInfoAdd.aspx";
				executeGetCommonClient(wwwUrl);

				// 获取箱型数组,设置空箱重量
				setConWeightMap(hcbb);

				// 查询车牌重量信息
				setTruckInfo(hcbb);

				// 添加进口报备资料
				wwwUrl = "http://tqbb.fjeport.gov.cn/ImportTrailerNumberInfo.ashx?__method=c&cntrnoStatus=1";
				nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("dockCode", hcbb.getDockCode()));
				nvps.add(new BasicNameValuePair("importContainerInfo.billNo", hcbb.getBillNo()));
				nvps.add(new BasicNameValuePair("importContainerInfo.billNo", hcbb.getBillNo2()));
				nvps.add(new BasicNameValuePair("importContainerInfo.cntrWeight", hcbb.getCntrWeight()));
				nvps.add(new BasicNameValuePair("importContainerInfo.cntrWeight", hcbb.getCntrWeight2()));
				nvps.add(new BasicNameValuePair("importContainerInfo.cntrno", hcbb.getCntrno()));
				nvps.add(new BasicNameValuePair("importContainerInfo.cntrno", hcbb.getCntrno2()));
				nvps.add(new BasicNameValuePair("importContainerInfo.eirinfoExist", ""));
				nvps.add(new BasicNameValuePair("importContainerInfo.eirinfoExist", ""));
				nvps.add(new BasicNameValuePair("importContainerInfo.eirno", ""));
				nvps.add(new BasicNameValuePair("importContainerInfo.eirno", ""));
				nvps.add(new BasicNameValuePair("importContainerInfo.sealno", hcbb.getSealNo()));
				nvps.add(new BasicNameValuePair("importContainerInfo.sealno", hcbb.getSealNo2()));
				nvps.add(new BasicNameValuePair("importContainerInfo.sizetypeofcntr", hcbb.getSizetypeofcntr()));
				nvps.add(new BasicNameValuePair("importContainerInfo.sizetypeofcntr", hcbb.getSizetypeofcntr2()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.addedFlag", "0"));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.cntrNo", hcbb.getTruckCntrNo()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.corpname", ""));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.corpno", ""));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.customsCode", "3708"));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.dockCode", hcbb.getDockCode()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.grossweight", hcbb.getGrossweight()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.msgStatus", "1"));// 0保存，1保存并发送
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.remark", ""));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.rfidSerialno", hcbb.getRfidSerialno()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.sealno", hcbb.getTruckSealno()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.tfcode", hcbb.getTfcode()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.tfcode1", hcbb.getTfcode1()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.trailerFrameWeight", hcbb.getTrailerFrameWeight()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.trailerWeight", hcbb.getTrailerWeight()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.typedescription", hcbb.getTypedescription()));
				nvps.add(new BasicNameValuePair("importTrailerNumberInfo.vehicleno", hcbb.getVehicleno()));
				
				rvalue = executeCommonClient(wwwUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shutdownClient();
		}
		return rvalue;
	}

	/**
	 * 设置空柜重
	 * 
	 * @param hcbb
	 * @throws Exception
	 */
	private void setConWeightMap(Hcbb hcbb) throws Exception {
		if (conWeightMap.size() == 0) {
			String wwwUrl = "http://tqbb.fjeport.gov.cn/Tbcodecntrts.ashx?__method=sa&q=2&limit=20";
			String html = executeGetCommonClient(wwwUrl);
			if (!StringManage.isEmpty(html)) {
				JSONObject json = JSONObject.fromObject(html);
				if (json.containsKey("TbcodecntrtsList")) {
					JSONArray jsonArr = json.getJSONArray("TbcodecntrtsList");
					for (int i = 0; i < jsonArr.size(); i++) {
						conWeightMap.put(jsonArr.getJSONObject(i).getString("Typesizeofcntr"), jsonArr.getJSONObject(i).getString("CntrWeight"));
					}
				}
			}
		}
		if (conWeightMap.containsKey(hcbb.getSizetypeofcntr())) {
			hcbb.setCntrWeight(conWeightMap.get(hcbb.getSizetypeofcntr()));
		}
		if (conWeightMap.containsKey(hcbb.getSizetypeofcntr2())) {
			hcbb.setCntrWeight2(conWeightMap.get(hcbb.getSizetypeofcntr2()));
		}
	}

	/**
	 * 获取车辆的空重和车辆编号
	 * 
	 * @param hcbb
	 * @throws Exception
	 */
	private void setTruckInfo(Hcbb hcbb) throws Exception {
		String wwwUrl = "http://tqbb.fjeport.gov.cn/TrailerNumberInfo.ashx?__method=t";
		nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("vehicleno", hcbb.getVehicleno()));
		String html = executeCommonClient(wwwUrl);
		hcbb.setTrailerWeight(getTruck(html, "Weight"));// 车辆重量
		hcbb.setRfidSerialno(getTruck(html, "RfidSerialNo"));// 车辆编号
	}

	/**
	 * 解析车辆
	 * 
	 * @param html
	 * @return
	 */
	private String getTruck(String jsonStr, String valueName) {
		String value = StringManage.FS_EMPTY;
		JSONObject json = JSONObject.fromObject(jsonStr);
		if (json.getString("Result").equals("success")) {
			JSONObject data = JSONObject.fromObject(json.get("TbtrailerResult"));
			value = data.getString(valueName);
		}
		return value;
	}

	@Override
	public String preExecute(FetchSearch search) throws Exception {
		return null;
	}

}
