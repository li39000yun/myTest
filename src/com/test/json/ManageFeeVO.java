package com.test.json;

public class ManageFeeVO {

	protected String truck = "";// 车牌
	protected String month = "";// 月份
	protected String operateManName = "";// 操作人
	protected String operateTime = "";// 操作时间
	protected String remark = "";// 备注
	protected ManageFee[] manageFees = new ManageFee[0];

	public String getTruck() {
		return truck;
	}

	public void setTruck(String truck) {
		this.truck = truck;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getOperateManName() {
		return operateManName;
	}

	public void setOperateManName(String operateManName) {
		this.operateManName = operateManName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ManageFee[] getManageFees() {
		return manageFees;
	}

	public void setManageFees(ManageFee[] manageFees) {
		this.manageFees = manageFees;
	}
	
}
