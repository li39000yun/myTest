package com.test.conn.zcd;

import com.kingsoft.control.util.StringManage;
import com.kingsoft.control.exception.DataBaseException;
import com.kingsoft.control.database.MappingTableModel;

/**
 * 业务应付费用
 * 
 * @author kingsoft
 * 
 * @version 2010-08-03
 * 
 * @since JDK 1.6.0_10-rc2
 * 
 */

public class BusiPayoutFee implements MappingTableModel {

	private static final long serialVersionUID = 1L;

	private static final String $MAPPING_TABLE_NAME = "busi_payout_fee";// 数据库表名称

	protected String busiId = StringManage.FS_EMPTY;// 承运单号,引用(busi_container.id)
	protected int sequence = 1;// 柜序号,引用(busi_container.sequence)
	protected int feeId = Integer.MIN_VALUE;// 费用编号,引用(base_fee.id)
	protected String accreditId = StringManage.FS_EMPTY;// 授权编号,(引用accredit_corporate.accredit_id)
	protected String feeName = StringManage.FS_EMPTY;// 费用名称
	protected double money = 0.00;// 金额
	protected double writeOffMoney = 0.00;// 核销金额
	protected String currency = StringManage.FS_EMPTY;// 币种
	protected int operator = 0;// 操作人,引用(sys_user.id)
	protected String operateTime = StringManage.FS_EMPTY;// 操作时间,格式:yyyy-MM-dd
	// hh:mm:ss
	protected int writeOffMan = 0;// 核销人,引用(sys_user.id)
	protected String writeOffTime = StringManage.FS_EMPTY;// 核销时间,格式:yyyy-MM-dd
	// hh:mm:ss
	protected String feePropertyId = "pay";// 费用性质,引用(global_variables.value)
	protected String payoutToUnitId = StringManage.FS_EMPTY;// 支付单位,引用(global_variables.value)
	protected String payoutToObject = StringManage.FS_EMPTY;// 支付对象
	protected int state = 0;// 当前状态,(0:未核销;1:部份核销;2:全部核销;)
	protected String applyId = StringManage.FS_EMPTY;// 申请单号
	protected String evidenceId = StringManage.FS_EMPTY;// 凭证单号
	protected String writeOffRemark = StringManage.FS_EMPTY;// 核销备注
	protected int isAudit = -1;// 是否审核(-1:未提交;0:否;1:是)，(注：运费类型的报销前需要审核,2014-12-16)
	protected int buildMan = 0;// 报销申请人
	protected String buildTime = StringManage.FS_EMPTY;// 报销时间
	protected int buildState = 0;// 申请状态,(0:未报销;1:部分报销;2:全部报销)
	protected double buildMoney = 0;// 报销金额
	protected int isHandWriteOff = 0;// 是否已手动核销(0:否;1:是)手动核销过不给报销
	protected int feeType = 0;// 费用类型(0:运输费用;1:司机费用)
	protected int isAppFee = 0;//是否为APP传过来的司机费用(0:否;1:是)
	protected int isPicture = 0;// app是否有照片
	protected String operatorName = StringManage.FS_EMPTY;// 操作员名称
	protected String syncTime = StringManage.FS_EMPTY;// 同步时间,格式:yyyy-MM-dd hh:mm:ss
	protected int isAskTcb = 0;// 是否向拖车宝发起修改请求(0:未;1:已)

	public BusiPayoutFee() {

	}

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

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public String getAccreditId() {
		return accreditId;
	}

	public void setAccreditId(String accreditId) {
		if (accreditId != null) {
			this.accreditId = accreditId;
		}
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		if (feeName != null) {
			this.feeName = feeName;
		}
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getWriteOffMoney() {
		return writeOffMoney;
	}

	public void setWriteOffMoney(double writeOffMoney) {
		this.writeOffMoney = writeOffMoney;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		if (currency != null) {
			this.currency = currency;
		}
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		if (operateTime != null) {
			this.operateTime = operateTime;
		}
	}

	public int getWriteOffMan() {
		return writeOffMan;
	}

	public void setWriteOffMan(int writeOffMan) {
		this.writeOffMan = writeOffMan;
	}

	public String getWriteOffTime() {
		return writeOffTime;
	}

	public void setWriteOffTime(String writeOffTime) {
		if (writeOffTime != null) {
			this.writeOffTime = writeOffTime;
		}
	}

	public String getFeePropertyId() {
		return feePropertyId;
	}

	public void setFeePropertyId(String feePropertyId) {
		if (feePropertyId != null) {
			this.feePropertyId = feePropertyId;
		}
	}

	public String getPayoutToUnitId() {
		return payoutToUnitId;
	}

	public void setPayoutToUnitId(String payoutToUnitId) {
		if (payoutToUnitId != null) {
			this.payoutToUnitId = payoutToUnitId;
		}
	}

	public String getPayoutToObject() {
		return payoutToObject;
	}

	public void setPayoutToObject(String payoutToObject) {
		if (payoutToObject != null) {
			this.payoutToObject = payoutToObject;
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		if (applyId != null) {
			this.applyId = applyId;
		}
	}

	public String getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(String evidenceId) {
		if (evidenceId != null) {
			this.evidenceId = evidenceId;
		}
	}

	public String getWriteOffRemark() {
		return writeOffRemark;
	}

	public void setWriteOffRemark(String writeOffRemark) {
		this.writeOffRemark = writeOffRemark;
	}

	public int getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}

	public int getBuildMan() {
		return buildMan;
	}

	public void setBuildMan(int buildMan) {
		this.buildMan = buildMan;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		if (buildTime != null) {
			this.buildTime = buildTime;
		}
	}

	public int getBuildState() {
		return buildState;
	}

	public void setBuildState(int buildState) {
		this.buildState = buildState;
	}

	public double getBuildMoney() {
		return buildMoney;
	}

	public void setBuildMoney(double buildMoney) {
		this.buildMoney = buildMoney;
	}

	public int getIsHandWriteOff() {
		return isHandWriteOff;
	}

	public void setIsHandWriteOff(int isHandWriteOff) {
		this.isHandWriteOff = isHandWriteOff;
	}

	public int getFeeType() {
		return feeType;
	}

	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}

	public int getIsAppFee() {
		return isAppFee;
	}

	public void setIsAppFee(int isAppFee) {
		this.isAppFee = isAppFee;
	}

	public int getIsPicture() {
		return isPicture;
	}

	public void setIsPicture(int isPicture) {
		this.isPicture = isPicture;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		if (operatorName != null) {
			this.operatorName = operatorName;
		}
	}
	
	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		if (syncTime != null) {
			this.syncTime = syncTime;
		}
	}

	public int getIsAskTcb() {
		return isAskTcb;
	}

	public void setIsAskTcb(int isAskTcb) {
		this.isAskTcb = isAskTcb;
	}

	public final String mappingTableName() {
		return $MAPPING_TABLE_NAME;
	}

	public void clone(MappingTableModel model) {
		if (!(model instanceof BusiPayoutFee)) {
			return;
		}
		BusiPayoutFee obj = (BusiPayoutFee) model;
		if (obj != null) {
			obj.busiId = busiId;
			obj.sequence = sequence;
			obj.feeId = feeId;
			obj.accreditId = accreditId;
			obj.feeName = feeName;
			obj.money = money;
			obj.writeOffMoney = writeOffMoney;
			obj.currency = currency;
			obj.operator = operator;
			obj.operateTime = operateTime;
			obj.writeOffMan = writeOffMan;
			obj.writeOffTime = writeOffTime;
			obj.feePropertyId = feePropertyId;
			obj.payoutToUnitId = payoutToUnitId;
			obj.payoutToObject = payoutToObject;
			obj.state = state;
			obj.applyId = applyId;
			obj.evidenceId = evidenceId;
			obj.writeOffRemark = writeOffRemark;
			obj.isAudit = isAudit;
			obj.buildMan = buildMan;
			obj.buildState = buildState;
			obj.buildTime = buildTime;
			obj.buildMoney = buildMoney;
			obj.isHandWriteOff = isHandWriteOff;
			obj.feeType = feeType;
			obj.isAppFee = isAppFee;
			obj.isPicture = isPicture;
			obj.operatorName = operatorName;
			obj.isAskTcb = isAppFee;
			obj.syncTime = syncTime;
		}
	}

	public void validate() throws DataBaseException {
		if (StringManage.isEmpty(busiId)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"承运单号 不能为空.");
		}
		if (StringManage.isEmpty(feeName)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"费用名称 不能为空.");
		}
		if (StringManage.isEmpty(currency)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"币种 不能为空.");
		}
		if (StringManage.isEmpty(operateTime)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"操作时间 不能为空.");
		}

	}
}