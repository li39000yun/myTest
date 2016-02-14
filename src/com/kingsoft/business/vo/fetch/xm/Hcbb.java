package com.kingsoft.business.vo.fetch.xm;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.kingsoft.control.Console;
import com.kingsoft.control.util.StringManage;

/**
 * 海沧保税港报备类
 * 
 * @author liyunqiang
 * 
 * @version 2015-7-10
 * 
 * @since JDK 1.6
 * 
 */
public class Hcbb implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String dockCode = StringManage.FS_EMPTY;// 码头编码
	protected String billNo = StringManage.FS_EMPTY;// 提单号1
	protected String billNo2 = StringManage.FS_EMPTY;// 提单号2
	protected String cntrWeight = StringManage.FS_EMPTY;// 柜重1
	protected String cntrWeight2 = StringManage.FS_EMPTY;// 柜重2
	protected String cntrno = StringManage.FS_EMPTY;// 柜号1
	protected String cntrno2 = StringManage.FS_EMPTY;// 柜号2
	protected String sealNo = StringManage.FS_EMPTY;// 封条号
	protected String sealNo2 = StringManage.FS_EMPTY;// 封条号2
	protected String sizetypeofcntr = StringManage.FS_EMPTY;// 箱型
	protected String sizetypeofcntr2 = StringManage.FS_EMPTY;// 箱型2
	protected String rfidSerialno = StringManage.FS_EMPTY;// 车辆编号
	protected String trailerWeight = StringManage.FS_EMPTY;// 车辆重量
	protected String vehicleno = StringManage.FS_EMPTY;// 车牌号
	protected String tfcode = "05";// 托架代码
	protected String tfcode1 = "05,5000,40’两排轮普通架";// 托架代码描述(默认40GP，20gp的取值 01,4500,20’普通架 )
	protected String trailerFrameWeight = "5000";// 托架重量
	protected String typedescription = "40’两排轮普通架";// 托架描述

	// 以下为扩展参数
	protected String dock = StringManage.FS_EMPTY;// 码头
	protected String userId = StringManage.FS_EMPTY;// 用户id
	protected String passwork = StringManage.FS_EMPTY;// 用户密码
	protected String corpNo = StringManage.FS_EMPTY;// cookies用户id
	protected int type = 0;// 进出口类型(0:进口;1:出口)

	public String getDockCode() {
		if (this.dock.indexOf("海润") > -1) {
			return "PHR";
		} else if (this.dock.indexOf("嵩屿") > -1) {
			return "PSY";
		} else if (this.dock.indexOf("国际") > -1) {
			return "PGJ";
		} else if (this.dock.indexOf("新海达") > -1) {
			return "PHD";
		} else if (this.dock.indexOf("远海") > -1) {
			return "PYH";
		}
		return dockCode;
	}

	public void setDockCode(String dockCode) {
		this.dockCode = dockCode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillNo2() {
		return billNo2;
	}

	public void setBillNo2(String billNo2) {
		this.billNo2 = billNo2;
	}

	public String getCntrWeight() {
		return cntrWeight;
	}

	public void setCntrWeight(String cntrWeight) {
		if (isNumeric(cntrWeight)) {
			this.cntrWeight = cntrWeight;
		}
	}

	public String getCntrWeight2() {
		return cntrWeight2;
	}

	public void setCntrWeight2(String cntrWeight2) {
		if (isNumeric(cntrWeight2)) {
			this.cntrWeight2 = cntrWeight2;
		}
	}

	public String getCntrno() {
		return cntrno;
	}

	public void setCntrno(String cntrno) {
		this.cntrno = cntrno;
	}

	public String getCntrno2() {
		return cntrno2;
	}

	public void setCntrno2(String cntrno2) {
		this.cntrno2 = cntrno2;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getSealNo2() {
		return sealNo2;
	}

	public void setSealNo2(String sealNo2) {
		this.sealNo2 = sealNo2;
	}

	public String getSizetypeofcntr() {
		return sizetypeofcntr;
	}

	public void setSizetypeofcntr(String sizetypeofcntr) {
		this.sizetypeofcntr = sizetypeofcntr;
	}

	public String getSizetypeofcntr2() {
		return sizetypeofcntr2;
	}

	public void setSizetypeofcntr2(String sizetypeofcntr2) {
		this.sizetypeofcntr2 = sizetypeofcntr2;
	}

	public String getTruckCntrNo() {
		if (StringManage.isEmpty(this.cntrno2)) {
			return this.cntrno;
		} else {
			return this.cntrno + "|" + this.cntrno2;
		}
	}

	public String getTruckSealno() {
		if (StringManage.isEmpty(this.sealNo2)) {
			return this.sealNo;
		} else {
			return this.sealNo + "|" + this.sealNo2;
		}
	}

	public String getRfidSerialno() {
		return rfidSerialno;
	}

	public void setRfidSerialno(String rfidSerialno) {
		this.rfidSerialno = rfidSerialno;
	}

	public String getTrailerWeight() {
		return trailerWeight;
	}

	public void setTrailerWeight(String trailerWeight) {
		this.trailerWeight = trailerWeight;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getGrossweight() {
		double total = 0;
		// 加空柜重
		if (isNumeric(cntrWeight)) {
			total = Console.FS_NUMBER.add(total, Double.parseDouble(cntrWeight), 2);
		}
		if (isNumeric(cntrWeight2)) {
			total = Console.FS_NUMBER.add(total, Double.parseDouble(cntrWeight2), 2);
		}
		// 加车辆重量
		if (isNumeric(trailerWeight)) {
			total = Console.FS_NUMBER.add(total, Double.parseDouble(trailerWeight), 2);
		}
		// 加托架重量
		if (this.sizetypeofcntr.indexOf("20") > -1) {
			// 20尺
			total = Console.FS_NUMBER.add(total, 4500, 2);
		} else {// 默认40尺
			total = Console.FS_NUMBER.add(total, 5000, 2);
		}
		return Double.toString(total);
	}

	// 判断传入字符串是否是数字
	public boolean isNumeric(String $) {
		Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*");
		return pattern.matcher($).matches();
	}

	public String getTfcode() {
		if (this.sizetypeofcntr.indexOf("20") > -1) {
			return "01";
		}
		return tfcode;
	}

	public void setTfcode(String tfcode) {
		this.tfcode = tfcode;
	}

	public String getTfcode1() {
		if (this.sizetypeofcntr.indexOf("20") > -1) {
			return "01,4500,20’普通架";
		}
		return tfcode1;
	}

	public String getTrailerFrameWeight() {
		if (this.sizetypeofcntr.indexOf("20") > -1) {
			return "4500";
		}
		return trailerFrameWeight;
	}

	public void setTrailerFrameWeight(String trailerFrameWeight) {
		this.trailerFrameWeight = trailerFrameWeight;
	}

	public void setTfcode1(String tfcode1) {
		this.tfcode1 = tfcode1;
	}

	public String getTypedescription() {
		if (this.sizetypeofcntr.indexOf("20") > -1) {
			return "20’普通架";
		}
		return typedescription;
	}

	public void setTypedescription(String typedescription) {
		this.typedescription = typedescription;
	}

	public String getDock() {
		return dock;
	}

	public void setDock(String dock) {
		this.dock = dock;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

	public String getCorpNo() {
		return corpNo;
	}

	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
