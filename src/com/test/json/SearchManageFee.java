package com.test.json;

public class SearchManageFee {

	protected String[] trucks = { "粤BS6830" };// 车牌
	protected String beginDate = "2015-09-01";//
	protected String endDate = "2015-09-10";//
	protected String accreditid = "zcd";// 备注
	protected String appkey = "52db4c7e86c81bad";
	protected int userId = 0;

	public String[] getTrucks() {
		return trucks;
	}

	public void setTrucks(String[] trucks) {
		this.trucks = trucks;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAccreditid() {
		return accreditid;
	}

	public void setAccreditid(String accreditid) {
		this.accreditid = accreditid;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
