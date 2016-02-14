package com.kingsoft.business.vo.fetch;

import java.io.Serializable;

import com.kingsoft.control.util.StringManage;

/**
 * 抓取箱货信息用来批量进口新增 对象
 * 
 * @author xiehui
 * 
 * @version 2015年5月20日
 * 
 * @since JDK 1.6
 * 
 */

public class FetchContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String busiId = StringManage.FS_EMPTY;// 业务编号,自动生成,格式:YYMM0001
	protected int sequence = 1;// 柜序号
	protected String accreditId = StringManage.FS_EMPTY;// 授权码,引用(accredit_corporate.accredit_id)
	protected String port = StringManage.FS_EMPTY;// 港区
	protected int customsType = 1;// 进出口类型(1:出口,2:进口)
	protected String containerNo = StringManage.FS_EMPTY;// 柜号
	protected String bookingNo = StringManage.FS_EMPTY;// 提单号
	protected String enterTime = StringManage.FS_EMPTY;// 进港时间
	private String fecthBeginTime = StringManage.FS_EMPTY;// 抓取开始时间
	protected String containerName = StringManage.FS_EMPTY;// 箱类
	protected String containerType = StringManage.FS_EMPTY;// 箱型
	protected String sealNo = StringManage.FS_EMPTY;// 铅封号
	protected String line = StringManage.FS_EMPTY;// 船公司
	protected String appointDate = StringManage.FS_EMPTY;// 做箱时间
	protected String ship = StringManage.FS_EMPTY;// 船名
	protected String voyage = StringManage.FS_EMPTY;// 航次
	protected String piece = "0";// 件数
	protected String weight = "0";// 毛重
	protected String volume = "0";// 体积

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		if (busiId != null) {
			this.busiId = busiId;
		}
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getAccreditId() {
		return accreditId;
	}

	public void setAccreditId(String accreditId) {
		if (accreditId != null) {
			this.accreditId = accreditId;
		}
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		if (port != null) {
			this.port = port;
		}
	}

	public int getCustomsType() {
		return customsType;
	}

	public void setCustomsType(int customsType) {
		this.customsType = customsType;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		if (containerNo != null) {
			this.containerNo = containerNo;
		}
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		if (enterTime != null) {
			this.enterTime = enterTime;
		}
	}

	public String getFecthBeginTime() {
		return fecthBeginTime;
	}

	public void setFecthBeginTime(String fecthBeginTime) {
		if (fecthBeginTime != null) {
			this.fecthBeginTime = fecthBeginTime;
		}
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
}
