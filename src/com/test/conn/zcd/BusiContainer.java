package com.test.conn.zcd;

import com.kingsoft.control.util.StringManage;
import com.kingsoft.control.exception.DataBaseException;
import com.kingsoft.control.database.MappingTableModel;

/**
 * 业务柜信息
 * 
 * @author kingsoft
 * 
 * @version 2010-08-03
 * 
 * @since JDK 1.6.0_10-rc2
 * 
 */

public class BusiContainer implements MappingTableModel {

	private static final long serialVersionUID = 1L;

	private static final String $MAPPING_TABLE_NAME = "busi_container";// 数据库表名称
	protected String busiId = StringManage.FS_EMPTY;// 承运单号,引用(busi_base.busi_id)
	protected int sequence = 1;// 柜序号
	protected String accreditId = StringManage.FS_EMPTY;// 授权编号,(引用accredit_corporate.accredit_id)
	protected int state = -1;// 当前状态,(-1:接单;0:待派车,1:已派车;2:已还柜;3:待录费用;4:已录费用;5:审核通过;6:取消;7:作废)
	protected int checkInFee = 0;// 是否已录费用,(0:未录入;1:已录入;)
	protected int isLock = 0;// 是否锁定(0:未锁定;1;锁定)
	protected String appointTime = StringManage.FS_EMPTY;// 做柜时间,格式:YYYY-MM-DD
	// hh:mm:ss
	protected String arriveTime = StringManage.FS_EMPTY;// 到厂时间,格式:YYYY-MM-DD
	// hh:mm:ss
	protected String leaveTime = StringManage.FS_EMPTY;// 离厂时间,格式:YYYY-MM-DD
	// hh:mm:ss
	protected String returnTime = StringManage.FS_EMPTY;// 还柜时间,格式:YYYY-MM-DD
	// hh:mm:ss
	protected int factoryId = Integer.MIN_VALUE;// 工厂编号,引用(base_factory.id)
	protected String factoryShortName = StringManage.FS_EMPTY;// 工厂简称
	protected String contact = StringManage.FS_EMPTY;// 联系人
	protected String telephone = StringManage.FS_EMPTY;// 联系电话
	protected String fax = StringManage.FS_EMPTY;// 传真号码
	protected String mobilePhone = StringManage.FS_EMPTY;// 手机号码
	protected String address = StringManage.FS_EMPTY;// 装货地址
	protected String loadPlace = StringManage.FS_EMPTY;// 装货地点
	protected int factoryId2 = Integer.MIN_VALUE;// 工厂编号2,引用(base_factory.id)
	protected String factoryShortName2 = StringManage.FS_EMPTY;// 工厂简称2
	protected String contact2 = StringManage.FS_EMPTY;// 联系人2
	protected String telephone2 = StringManage.FS_EMPTY;// 联系电话2
	protected String mobilePhone2 = StringManage.FS_EMPTY;// 手机号码2
	protected String address2 = StringManage.FS_EMPTY;// 装货地址2
	protected String loadPlace2 = StringManage.FS_EMPTY;// 装货地点2
	protected String containerType = StringManage.FS_EMPTY;// 柜型
	protected String containerNo = StringManage.FS_EMPTY;// 柜号
	protected String sealNo = StringManage.FS_EMPTY;// 封条号
	protected double containerWeight = 0.00;// 柜重
	protected String otherBookingNo = StringManage.FS_EMPTY;// 订舱号2
	protected String otherContainerNo = StringManage.FS_EMPTY;// 柜号2
	protected String otherSealNo = StringManage.FS_EMPTY;// 封条号2
	protected double otherContainerWeight = 0.00;// 柜重2
	protected double weight = 0.00;// 总重量
	protected String supplyTime = StringManage.FS_EMPTY;// 补料时间,格式:YYYY-MM-DD
	// hh:mm
	protected String cutOffTime = StringManage.FS_EMPTY;// 截关时间,格式:YYYY-MM-DD
	// hh:mm
	protected int isDifferentGet = 0;// 是否异提(0:否;1:是;)
	protected int isDoubleCon = 1;// 是否孖托(1:单柜;2:孖拖;)
	protected String getConPile = StringManage.FS_EMPTY;// 提柜堆场
	protected String getConPlace = StringManage.FS_EMPTY;// 提柜地点
	protected String returnConPile = StringManage.FS_EMPTY;// 还柜堆场
	protected String returnConPlace = StringManage.FS_EMPTY;// 还柜地点
	protected int truckId = 0;// 车牌号, 引用(base_truck.id)
	protected int transportTeamId = 0;// 车队,引用(base_transport_team.id)
	protected int driverId = 0;// 司机,引用(base_driver.id)
	protected int truckShelfId = 0;// 拖架号,引用(base_transport_shelf.id)
	protected int stayNight = 0;// 压夜数量
	protected String cargoSO = StringManage.FS_EMPTY;// 散货SO号
	protected int warehouseId = 0;// 仓库编号,引用(base_warehouse.id)
	protected String warehouse = StringManage.FS_EMPTY;// 仓库名称
	protected double cubage = 0.00;// 立方数
	protected int piece = 0;// 件数
	protected String goodName = StringManage.FS_EMPTY;// 货名
	protected int manageMode = 0;// 经营方式（0:内管车;1:外派车;2:挂靠车）
	protected double kilometres = 0.0;// 公里数
	protected double factOilQuantity = 0.0;// 实际油量
	protected String truck = StringManage.FS_EMPTY;// 车牌号
	protected String transportTeam = StringManage.FS_EMPTY;// 车队
	protected String driver = StringManage.FS_EMPTY;// 司机
	protected double driverCost = 0;// 司机产值
	protected String driverMobilePhone = StringManage.FS_EMPTY;// 司机手机
	protected String truckShelf = StringManage.FS_EMPTY;// 拖架号
	protected String shipperRemark = StringManage.FS_EMPTY;// 托运单备注
	protected String followRemark = StringManage.FS_EMPTY;// 跟单备注
	protected String incomeRemark = StringManage.FS_EMPTY;// 应收备注
	protected String payoutRemark = StringManage.FS_EMPTY;// 应付备注
	protected String customerJobNo = StringManage.FS_EMPTY;// 客户工作号
	protected double standConsume = 0;// 标准油耗
	protected double wastageOilKm = 0;// 百公里油耗
	protected int isPrint = 0;// 是否打印过(0:否;1是)
	protected int isReturnSend = 0;// 还柜参数是否已发送(0:否;1:是)
	protected int isGetSend = 0;// 提柜参数是否已发送(0:否;1:是)
	protected int isPortSend = 1;// 是否发送码头查询(0:否;1:是)
	protected String greenTightTime = StringManage.FS_EMPTY;// 放行时间
	protected String truckState = StringManage.FS_EMPTY;// 拖车状态
	protected String customsNo = StringManage.FS_EMPTY;// 海关编号
	protected String externalBusiId = StringManage.FS_EMPTY;// 客户在线下单承运单号
	protected int isCompose = 0;// 是否存在配货(0:不存在;大于0存在)
	protected String composeId = StringManage.FS_EMPTY;// 配货编号
	protected String togetherId = StringManage.FS_EMPTY;// 拼箱编号
	protected double advanceMoney = 0;// 预付费
	protected double writeOffMoney = 0;// 预付费核销金额
	protected double writeOffState = 0;// 预付费核销状态(0:未核销;1:部份核销;2:全部核销;)
	protected String writeOffTime = StringManage.FS_EMPTY;// 预付费核销时间
	protected int writeOffMan = 0;// 预付费核销人
	protected double overheadExpenses = 0.00;// 扣管理费（挂靠车从运费中扣除）
	protected String auditMan = StringManage.FS_EMPTY;// 审核人
	protected String auditTime = StringManage.FS_EMPTY;// 审核时间
	protected String endPort = StringManage.FS_EMPTY;// 目的港
	protected int isSendFactory = 0;// 是否已发送到厂短信(0:未发送;1:未排已发;2:在途已发)
	protected String toNo = StringManage.FS_EMPTY;// TO号
	protected String joNo = StringManage.FS_EMPTY;// JO号，客户工作号
	protected String orderNo = StringManage.FS_EMPTY;// 订单号
	protected String damcoBookingNo = StringManage.FS_EMPTY;// 丹马士订舱号
	protected String tallyClerk = StringManage.FS_EMPTY;// 理货员
	protected int tallyClerkId = 0;// 理货员id(引用base_tally_clerk.id)
	protected String bookingArriveTime = StringManage.FS_EMPTY;// 预约到厂时间(已屏蔽不使用)
	protected String shipLeaveTime = StringManage.FS_EMPTY;// 船开时间
	protected String arriveCustomTime = StringManage.FS_EMPTY;// 到达海关时间(已屏蔽不使用)
	protected String leaveCustomTime = StringManage.FS_EMPTY;// 离开海关时间(已屏蔽不使用)
	protected String arriveFieldTime = StringManage.FS_EMPTY;// 到达关场时间
	protected String leaveFieldTime = StringManage.FS_EMPTY;// 离开关场时间
	protected String arriveWarehouseTime = StringManage.FS_EMPTY;// 到达仓库时间
	protected String beginLoadTime = StringManage.FS_EMPTY;// 在仓库开始装货时间
	protected String handInDockTime = StringManage.FS_EMPTY;// 重柜交码头时间(已屏蔽不使用)
	protected String arriveDockTime = StringManage.FS_EMPTY;// 到达码头时间
	protected String unloadNo = StringManage.FS_EMPTY;// 散货落货纸号
	protected String cutOffWarehouseTime = StringManage.FS_EMPTY;// 截仓时间
	protected String svoNo = StringManage.FS_EMPTY;// 散货SVO号
	protected String arriveGetPileTime = StringManage.FS_EMPTY;// 到达提柜堆场时间
	protected String leaveGetPileTime = StringManage.FS_EMPTY;// 离开提柜堆场时间
	protected int realPiece = 0;// 实际出货件数
	protected String packingSize = StringManage.FS_EMPTY;// 包装尺寸
	protected int weightUnit = 0;// 重量单位(0:吨;1:KG;2:磅;)
	protected int pieceUnit = 0;// 件数单位(0:件;1:纸箱;2:卡板;3:拖盘;4:扎)
	protected String leaveWarehouseTime = StringManage.FS_EMPTY;// 到达仓库时间
	protected String customsRemark = StringManage.FS_EMPTY;// 应付报关备注
	protected String customerRemark = StringManage.FS_EMPTY;// 应付客户备注
	protected String transportTeamRemark = StringManage.FS_EMPTY;// 应付车队备注
	protected String shipRemark = StringManage.FS_EMPTY;// 应付船公司备注
	protected String tallyClerkRemark = StringManage.FS_EMPTY;// 应付理货员备注
	protected String otherRemark = StringManage.FS_EMPTY;// 应付其它备注
	protected String customsInRemark = StringManage.FS_EMPTY;// 应收报关备注
	protected String customerInRemark = StringManage.FS_EMPTY;// 应收客户备注
	protected int auditState = 0;// 审核状态(0:未审核;1:应收已审;2:应付已审;3:审核通过)
	protected String writeOffRemark = StringManage.FS_EMPTY;// 预付费核销备注
	protected String imageName = StringManage.FS_EMPTY;// 上传的图片名称
	protected int isHaveCustoms = 0;// 工厂是否存在报关资料
	protected String schedulerNo = StringManage.FS_EMPTY;// 派车单号
	protected String specialCaseRemark = StringManage.FS_EMPTY;// 异常情况描述
	protected String specialCaseRemarkTime = StringManage.FS_EMPTY;// 异常情况修改时间
	protected int isOrderContainer = 0;//是否约柜(0:否;1:是)
	protected String printDate = StringManage.FS_EMPTY;// 打单日期
	protected String printCompany = StringManage.FS_EMPTY;// 打单公司
	protected int isPrintBill = 0;// 是否打单(0:否;1:是)
	protected int isExportPayout = 0;// 是否导出过应付对账单(0:否;1:是)
	protected int isPressNight = 0;// 是否压夜(0:否;1:是)
	protected double logisticsInsurance = 0;// 物流责任险
	protected String place = StringManage.FS_EMPTY;//交仓地点
	protected int yfState = 0;// 应付运费状态(0:未提交;1:提交;2:已审核)
	protected double transportFee = 0.00; //拖车费
	protected int outOfState = 0; // 出车状态 (0:出车未结束;1:出车结束)
	protected int sendOrderState = 0; // 派单状态(0:初始值;1:预抢单;2:预派单)
	protected int driverState = 0; // 司机状态(0:初始值;1:司机接单;2:司机拒单;3:无人抢单)
	protected int confirmDriverFee = 0; // 司机确认费用状态(0:未确认;1:已确认)
	protected int isCancel = 0;// 是否撤单(0:否;1:是)
	protected int dispatchConfirmFee = 0; // 调度确认费用状态(0:未确认;1:已确认)
	protected int checkDriverFee = 0;// 司机费用录入状态(0:未录入;1:已录入)
	protected String conSealNoRemark = StringManage.FS_EMPTY;// 箱封号备注
	protected int checkMan = 0;//复核人引用user_id
	protected String checkTime = StringManage.FS_EMPTY;//复核时间
	protected String checkRemark = StringManage.FS_EMPTY;//复核备注
	protected double lng = 0.0;// 装货地址经度
	protected double lat= 0.0;// 装货地址纬度
	protected String advanceTime = StringManage.FS_EMPTY;
	protected String overheadExpensesTime = StringManage.FS_EMPTY;
	protected String logisticsInsuranceTime = StringManage.FS_EMPTY;
	
	public BusiContainer() {

	}

	public int getIsPressNight() {
		return isPressNight;
	}

	public void setIsPressNight(int isPressNight) {
		this.isPressNight = isPressNight;
	}

	public int getIsOrderContainer() {
		return isOrderContainer;
	}

	public void setIsOrderContainer(int isOrderContainer) {
		this.isOrderContainer = isOrderContainer;
	}

	public String getAuditMan() {
		return auditMan;
	}

	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
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

	public String getAccreditId() {
		return accreditId;
	}

	public void setAccreditId(String accreditId) {
		if (accreditId != null) {
			this.accreditId = accreditId;
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public String getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(String appointTime) {
		if (appointTime != null) {
			this.appointTime = appointTime;
		}
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		if (arriveTime != null) {
			this.arriveTime = arriveTime;
		}
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		if (leaveTime != null) {
			this.leaveTime = leaveTime;
		}
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public int getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(int factoryId) {
		this.factoryId = factoryId;
	}

	public String getFactoryShortName() {
		return factoryShortName;
	}

	public void setFactoryShortName(String factoryShortName) {
		if (factoryShortName != null) {
			this.factoryShortName = factoryShortName;
		}
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		if (contact != null) {
			this.contact = contact;
		}
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		if (telephone != null) {
			this.telephone = telephone;
		}
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		if (fax != null) {
			this.fax = fax;
		}
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		if (mobilePhone != null) {
			this.mobilePhone = mobilePhone;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		}
	}

	public String getLoadPlace() {
		return loadPlace;
	}

	public void setLoadPlace(String loadPlace) {
		if (loadPlace != null) {
			this.loadPlace = loadPlace;
		}
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		if (containerType != null) {
			this.containerType = containerType;
		}
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		if (containerNo != null) {
			this.containerNo = containerNo;
		}
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		if (sealNo != null) {
			this.sealNo = sealNo;
		}
	}

	public double getContainerWeight() {
		return containerWeight;
	}

	public void setContainerWeight(double containerWeight) {
		this.containerWeight = containerWeight;
	}

	public String getOtherBookingNo() {
		return otherBookingNo;
	}

	public void setOtherBookingNo(String otherBookingNo) {
		if (otherBookingNo != null) {
			this.otherBookingNo = otherBookingNo;
		}
	}

	public String getOtherContainerNo() {
		return otherContainerNo;
	}

	public void setOtherContainerNo(String otherContainerNo) {
		if (otherContainerNo != null) {
			this.otherContainerNo = otherContainerNo;
		}
	}

	public String getOtherSealNo() {
		return otherSealNo;
	}

	public void setOtherSealNo(String otherSealNo) {
		if (otherSealNo != null) {
			this.otherSealNo = otherSealNo;
		}
	}

	public double getOtherContainerWeight() {
		return otherContainerWeight;
	}

	public void setOtherContainerWeight(double otherContainerWeight) {
		this.otherContainerWeight = otherContainerWeight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getSupplyTime() {
		return supplyTime;
	}

	public void setSupplyTime(String supplyTime) {
		if (supplyTime != null) {
			this.supplyTime = supplyTime;
		}
	}

	public String getCutOffTime() {
		return cutOffTime;
	}

	public void setCutOffTime(String cutOffTime) {
		if (cutOffTime != null) {
			this.cutOffTime = cutOffTime;
		}
	}

	public int getIsDifferentGet() {
		return isDifferentGet;
	}

	public void setIsDifferentGet(int isDifferentGet) {
		this.isDifferentGet = isDifferentGet;
	}

	public String getGetConPile() {
		return getConPile;
	}

	public void setGetConPile(String getConPile) {
		if (getConPile != null) {
			this.getConPile = getConPile;
		}
	}

	public String getGetConPlace() {
		return getConPlace;
	}

	public void setGetConPlace(String getConPlace) {
		if (getConPlace != null) {
			this.getConPlace = getConPlace;
		}
	}

	public String getReturnConPile() {
		return returnConPile;
	}

	public void setReturnConPile(String returnConPile) {
		if (returnConPile != null) {
			this.returnConPile = returnConPile;
		}
	}

	public String getReturnConPlace() {
		return returnConPlace;
	}

	public void setReturnConPlace(String returnConPlace) {
		if (returnConPlace != null) {
			this.returnConPlace = returnConPlace;
		}
	}

	public int getTruckId() {
		return truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public int getTransportTeamId() {
		return transportTeamId;
	}

	public void setTransportTeamId(int transportTeamId) {
		this.transportTeamId = transportTeamId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getTruckShelfId() {
		return truckShelfId;
	}

	public void setTruckShelfId(int truckShelfId) {
		this.truckShelfId = truckShelfId;
	}

	public int getStayNight() {
		return stayNight;
	}

	public void setStayNight(int stayNight) {
		this.stayNight = stayNight;
	}

	public String getCargoSO() {
		return cargoSO;
	}

	public void setCargoSO(String cargoSO) {
		if (cargoSO != null) {
			this.cargoSO = cargoSO;
		}
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		if (warehouse != null) {
			this.warehouse = warehouse;
		}
	}

	public double getCubage() {
		return cubage;
	}

	public void setCubage(double cubage) {
		this.cubage = cubage;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		if (goodName != null) {
			this.goodName = goodName;
		}
	}

	public int getCheckInFee() {
		return checkInFee;
	}

	public void setCheckInFee(int checkInFee) {
		this.checkInFee = checkInFee;
	}

	public int getManageMode() {
		return manageMode;
	}

	public void setManageMode(int manageMode) {
		this.manageMode = manageMode;
	}

	public double getKilometres() {
		return kilometres;
	}

	public void setKilometres(double kilometres) {
		this.kilometres = kilometres;
	}

	public double getFactOilQuantity() {
		return factOilQuantity;
	}

	public void setFactOilQuantity(double factOilQuantity) {
		this.factOilQuantity = factOilQuantity;
	}

	public String getTruck() {
		return truck;
	}

	public void setTruck(String truck) {
		if (truck != null) {
			this.truck = truck;
		}
	}

	public String getTransportTeam() {
		return transportTeam;
	}

	public void setTransportTeam(String transportTeam) {
		if (transportTeam != null) {
			this.transportTeam = transportTeam;
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		if (driver != null) {
			this.driver = driver;
		}
	}

	public double getDriverCost() {
		return driverCost;
	}

	public void setDriverCost(double driverCost) {
		this.driverCost = driverCost;
	}

	public String getDriverMobilePhone() {
		return driverMobilePhone;
	}

	public void setDriverMobilePhone(String driverMobilePhone) {
		if (driverMobilePhone != null) {
			this.driverMobilePhone = driverMobilePhone;
		}
	}

	public String getTruckShelf() {
		return truckShelf;
	}

	public void setTruckShelf(String truckShelf) {
		if (truckShelf != null) {
			this.truckShelf = truckShelf;
		}
	}

	public String getShipperRemark() {
		return shipperRemark;
	}

	public void setShipperRemark(String shipperRemark) {
		if (shipperRemark != null) {
			this.shipperRemark = shipperRemark;
		}
	}

	public String getFollowRemark() {
		return followRemark;
	}

	public void setFollowRemark(String followRemark) {
		if (followRemark != null) {
			this.followRemark = followRemark;
		}
	}

	public String getIncomeRemark() {
		return incomeRemark;
	}

	public void setIncomeRemark(String incomeRemark) {
		if (incomeRemark != null) {
			this.incomeRemark = incomeRemark;
		}
	}

	public String getPayoutRemark() {
		return payoutRemark;
	}

	public void setPayoutRemark(String payoutRemark) {
		if (payoutRemark != null) {
			this.payoutRemark = payoutRemark;
		}
	}

	public int getIsDoubleCon() {
		return isDoubleCon;
	}

	public void setIsDoubleCon(int isDoubleCon) {
		this.isDoubleCon = isDoubleCon;
	}

	public String getCustomerJobNo() {
		return customerJobNo;
	}

	public void setCustomerJobNo(String customerJobNo) {
		if (customerJobNo != null) {
			this.customerJobNo = customerJobNo;
		}
	}

	public double getStandConsume() {
		return standConsume;
	}

	public void setStandConsume(double standConsume) {
		this.standConsume = standConsume;
	}

	public double getWastageOilKm() {
		return wastageOilKm;
	}

	public void setWastageOilKm(double wastageOilKm) {
		this.wastageOilKm = wastageOilKm;
	}

	public int getFactoryId2() {
		return factoryId2;
	}

	public void setFactoryId2(int factoryId2) {
		this.factoryId2 = factoryId2;
	}

	public String getFactoryShortName2() {
		return factoryShortName2;
	}

	public void setFactoryShortName2(String factoryShortName2) {
		this.factoryShortName2 = factoryShortName2;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLoadPlace2() {
		return loadPlace2;
	}

	public void setLoadPlace2(String loadPlace2) {
		this.loadPlace2 = loadPlace2;
	}

	public int getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}

	public int getIsReturnSend() {
		return isReturnSend;
	}

	public void setIsReturnSend(int isReturnSend) {
		this.isReturnSend = isReturnSend;
	}

	public int getIsGetSend() {
		return isGetSend;
	}

	public void setIsGetSend(int isGetSend) {
		this.isGetSend = isGetSend;
	}

	public int getIsPortSend() {
		return isPortSend;
	}

	public void setIsPortSend(int isPortSend) {
		this.isPortSend = isPortSend;
	}

	public String getGreenTightTime() {
		return greenTightTime;
	}

	public void setGreenTightTime(String greenTightTime) {
		if (greenTightTime != null) {
			this.greenTightTime = greenTightTime;
		}
	}

	public String getTruckState() {
		return truckState;
	}

	public void setTruckState(String truckState) {
		if (truckState != null) {
			this.truckState = truckState;
		}
	}

	public String getCustomsNo() {
		return customsNo;
	}

	public void setCustomsNo(String customsNo) {
		if (customsNo != null) {
			this.customsNo = customsNo;
		}
	}

	public String getExternalBusiId() {
		return externalBusiId;
	}

	public void setExternalBusiId(String externalBusiId) {
		if (externalBusiId != null)
			this.externalBusiId = externalBusiId;
	}

	public int getIsCompose() {
		return isCompose;
	}

	public void setIsCompose(int isCompose) {
		this.isCompose = isCompose;
	}

	public String getComposeId() {
		return composeId;
	}

	public void setComposeId(String composeId) {
		if (composeId != null)
			this.composeId = composeId;
	}

	public String getTogetherId() {
		return togetherId;
	}

	public void setTogetherId(String togetherId) {
		if (togetherId != null)
			this.togetherId = togetherId;
	}

	public double getAdvanceMoney() {
		return advanceMoney;
	}

	public void setAdvanceMoney(double advanceMoney) {
		this.advanceMoney = advanceMoney;
	}

	public double getWriteOffMoney() {
		return writeOffMoney;
	}

	public void setWriteOffMoney(double writeOffMoney) {
		this.writeOffMoney = writeOffMoney;
	}

	public double getWriteOffState() {
		return writeOffState;
	}

	public void setWriteOffState(double writeOffState) {
		this.writeOffState = writeOffState;
	}

	public String getWriteOffTime() {
		return writeOffTime;
	}

	public void setWriteOffTime(String writeOffTime) {
		if (writeOffTime != null)
			this.writeOffTime = writeOffTime;
	}

	public int getWriteOffMan() {
		return writeOffMan;
	}

	public void setWriteOffMan(int writeOffMan) {
		this.writeOffMan = writeOffMan;
	}

	public double getOverheadExpenses() {
		return overheadExpenses;
	}

	public void setOverheadExpenses(double overheadExpenses) {
		this.overheadExpenses = overheadExpenses;
	}

	public String getEndPort() {
		return endPort;
	}

	public void setEndPort(String endPort) {
		if (endPort != null) {
			this.endPort = endPort;
		}
	}

	public int getIsSendFactory() {
		return isSendFactory;
	}

	public void setIsSendFactory(int isSendFactory) {
		this.isSendFactory = isSendFactory;
	}

	public String getToNo() {
		return toNo;
	}

	public void setToNo(String toNo) {
		if (toNo != null)
			this.toNo = toNo;
	}

	public String getJoNo() {
		return joNo;
	}

	public void setJoNo(String joNo) {
		if (joNo != null)
			this.joNo = joNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		if (orderNo != null)
			this.orderNo = orderNo;
	}

	public String getDamcoBookingNo() {
		return damcoBookingNo;
	}

	public void setDamcoBookingNo(String damcoBookingNo) {
		if (damcoBookingNo != null)
			this.damcoBookingNo = damcoBookingNo;
	}

	public String getTallyClerk() {
		return tallyClerk;
	}

	public void setTallyClerk(String tallyClerk) {
		if (tallyClerk != null)
			this.tallyClerk = tallyClerk;
	}

	public int getTallyClerkId() {
		return tallyClerkId;
	}

	public void setTallyClerkId(int tallyClerkId) {
		this.tallyClerkId = tallyClerkId;
	}

	public String getBookingArriveTime() {
		return bookingArriveTime;
	}

	public void setBookingArriveTime(String bookingArriveTime) {
		if (bookingArriveTime != null)
			this.bookingArriveTime = bookingArriveTime;
	}

	public String getShipLeaveTime() {
		return shipLeaveTime;
	}

	public void setShipLeaveTime(String shipLeaveTime) {
		if (shipLeaveTime != null)
			this.shipLeaveTime = shipLeaveTime;
	}

	public String getArriveCustomTime() {
		return arriveCustomTime;
	}

	public void setArriveCustomTime(String arriveCustomTime) {
		if (arriveCustomTime != null)
			this.arriveCustomTime = arriveCustomTime;
	}

	public String getLeaveCustomTime() {
		return leaveCustomTime;
	}

	public void setLeaveCustomTime(String leaveCustomTime) {
		if (leaveCustomTime != null)
			this.leaveCustomTime = leaveCustomTime;
	}

	public String getArriveFieldTime() {
		return arriveFieldTime;
	}

	public void setArriveFieldTime(String arriveFieldTime) {
		if (arriveFieldTime != null)
			this.arriveFieldTime = arriveFieldTime;
	}

	public String getLeaveFieldTime() {
		return leaveFieldTime;
	}

	public void setLeaveFieldTime(String leaveFieldTime) {
		if (leaveFieldTime != null)
			this.leaveFieldTime = leaveFieldTime;
	}

	public String getArriveWarehouseTime() {
		return arriveWarehouseTime;
	}

	public void setArriveWarehouseTime(String arriveWarehouseTime) {
		if (arriveWarehouseTime != null)
			this.arriveWarehouseTime = arriveWarehouseTime;
	}

	public String getBeginLoadTime() {
		return beginLoadTime;
	}

	public void setBeginLoadTime(String beginLoadTime) {
		if (beginLoadTime != null)
			this.beginLoadTime = beginLoadTime;
	}

	public String getHandInDockTime() {
		return handInDockTime;
	}

	public void setHandInDockTime(String handInDockTime) {
		if (handInDockTime != null)
			this.handInDockTime = handInDockTime;
	}

	public String getArriveDockTime() {
		return arriveDockTime;
	}

	public void setArriveDockTime(String arriveDockTime) {
		if (arriveDockTime != null)
			this.arriveDockTime = arriveDockTime;
	}

	public String getUnloadNo() {
		return unloadNo;
	}

	public void setUnloadNo(String unloadNo) {
		if (unloadNo != null)
			this.unloadNo = unloadNo;
	}

	public String getCutOffWarehouseTime() {
		return cutOffWarehouseTime;
	}

	public void setCutOffWarehouseTime(String cutOffWarehouseTime) {
		if (cutOffWarehouseTime != null)
			this.cutOffWarehouseTime = cutOffWarehouseTime;
	}

	public String getSvoNo() {
		return svoNo;
	}

	public void setSvoNo(String svoNo) {
		if (svoNo != null)
			this.svoNo = svoNo;
	}

	public String getArriveGetPileTime() {
		return arriveGetPileTime;
	}

	public void setArriveGetPileTime(String arriveGetPileTime) {
		if (arriveGetPileTime != null)
			this.arriveGetPileTime = arriveGetPileTime;
	}

	public String getLeaveGetPileTime() {
		return leaveGetPileTime;
	}

	public void setLeaveGetPileTime(String leaveGetPileTime) {
		if (leaveGetPileTime != null)
			this.leaveGetPileTime = leaveGetPileTime;
	}

	public int getRealPiece() {
		return realPiece;
	}

	public void setRealPiece(int realPiece) {
		this.realPiece = realPiece;
	}

	public String getPackingSize() {
		return packingSize;
	}

	public void setPackingSize(String packingSize) {
		if (packingSize != null)
			this.packingSize = packingSize;
	}

	public int getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(int weightUnit) {
		this.weightUnit = weightUnit;
	}

	public int getPieceUnit() {
		return pieceUnit;
	}

	public void setPieceUnit(int pieceUnit) {
		this.pieceUnit = pieceUnit;
	}

	public String getLeaveWarehouseTime() {
		return leaveWarehouseTime;
	}

	public void setLeaveWarehouseTime(String leaveWarehouseTime) {
		if (leaveWarehouseTime != null)
			this.leaveWarehouseTime = leaveWarehouseTime;
	}

	public String getCustomsRemark() {
		return customsRemark;
	}

	public void setCustomsRemark(String customsRemark) {
		if (customsRemark != null)
			this.customsRemark = customsRemark;
	}

	public String getTransportTeamRemark() {
		return transportTeamRemark;
	}

	public void setTransportTeamRemark(String transportTeamRemark) {
		if (transportTeamRemark != null)
			this.transportTeamRemark = transportTeamRemark;
	}

	public String getShipRemark() {
		return shipRemark;
	}

	public void setShipRemark(String shipRemark) {
		if (shipRemark != null)
			this.shipRemark = shipRemark;
	}

	public String getTallyClerkRemark() {
		return tallyClerkRemark;
	}

	public void setTallyClerkRemark(String tallyClerkRemark) {
		if (tallyClerkRemark != null)
			this.tallyClerkRemark = tallyClerkRemark;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		if (otherRemark != null)
			this.otherRemark = otherRemark;
	}

	public String getCustomsInRemark() {
		return customsInRemark;
	}

	public void setCustomsInRemark(String customsInRemark) {
		if (customsInRemark != null)
			this.customsInRemark = customsInRemark;
	}

	public String getCustomerInRemark() {
		return customerInRemark;
	}

	public void setCustomerInRemark(String customerInRemark) {
		if (customerInRemark != null)
			this.customerInRemark = customerInRemark;
	}

	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		if (customerRemark != null)
			this.customerRemark = customerRemark;
	}

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public String getWriteOffRemark() {
		return writeOffRemark;
	}

	public void setWriteOffRemark(String writeOffRemark) {
		if (writeOffRemark != null) {
			this.writeOffRemark = writeOffRemark;
		}
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		if (imageName != null) {
			this.imageName = imageName;
		}
	}

	public int getIsHaveCustoms() {
		return isHaveCustoms;
	}

	public void setIsHaveCustoms(int isHaveCustoms) {
		this.isHaveCustoms = isHaveCustoms;
	}

	public String getSchedulerNo() {
		return schedulerNo;
	}

	public void setSchedulerNo(String schedulerNo) {
		if (schedulerNo != null) {
			this.schedulerNo = schedulerNo;
		}
	}

	public final String mappingTableName() {
		return $MAPPING_TABLE_NAME;
	}

	public String getSpecialCaseRemark() {
		return specialCaseRemark;
	}

	public void setSpecialCaseRemark(String specialCaseRemark) {
		if (specialCaseRemark != null) {
			this.specialCaseRemark = specialCaseRemark;
		}
	}

	public String getSpecialCaseRemarkTime() {
		return specialCaseRemarkTime;
	}

	public void setSpecialCaseRemarkTime(String specialCaseRemarkTime) {
		if (specialCaseRemarkTime != null) {
			this.specialCaseRemarkTime = specialCaseRemarkTime;
		}
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		if (printDate != null) {
			this.printDate = printDate;
		}
	}

	public String getPrintCompany() {
		return printCompany;
	}

	public void setPrintCompany(String printCompany) {
		if (printCompany != null) {
			this.printCompany = printCompany;
		}
	}

	public int getIsPrintBill() {
		return isPrintBill;
	}

	public void setIsPrintBill(int isPrintBill) {
		this.isPrintBill = isPrintBill;
	}

	public int getIsExportPayout() {
		return isExportPayout;
	}

	public void setIsExportPayout(int isExportPayout) {
		this.isExportPayout = isExportPayout;
	}

	public double getLogisticsInsurance() {
		return logisticsInsurance;
	}

	public void setLogisticsInsurance(double logisticsInsurance) {
		this.logisticsInsurance = logisticsInsurance;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(double transportFee) {
		this.transportFee = transportFee;
	}

	public int getOutOfState() {
		return outOfState;
	}

	public void setOutOfState(int outOfState) {
		this.outOfState = outOfState;
	}

	public int getSendOrderState() {
		return sendOrderState;
	}

	public void setSendOrderState(int sendOrderState) {
		this.sendOrderState = sendOrderState;
	}

	public int getDriverState() {
		return driverState;
	}

	public void setDriverState(int driverState) {
		this.driverState = driverState;
	}

	public int getConfirmDriverFee() {
		return confirmDriverFee;
	}

	public void setConfirmDriverFee(int confirmDriverFee) {
		this.confirmDriverFee = confirmDriverFee;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	public int getDispatchConfirmFee() {
		return dispatchConfirmFee;
	}

	public void setDispatchConfirmFee(int dispatchConfirmFee) {
		this.dispatchConfirmFee = dispatchConfirmFee;
	}

	public int getYfState() {
		return yfState;
	}

	public void setYfState(int yfState) {
		this.yfState = yfState;
	}

	public int getCheckDriverFee() {
		return checkDriverFee;
	}

	public void setCheckDriverFee(int checkDriverFee) {
		this.checkDriverFee = checkDriverFee;
	}

	public String getConSealNoRemark() {
		return conSealNoRemark;
	}

	public void setConSealNoRemark(String conSealNoRemark) {
		this.conSealNoRemark = conSealNoRemark;
	}

	public int getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(int checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime;
	}

	public String getOverheadExpensesTime() {
		return overheadExpensesTime;
	}

	public void setOverheadExpensesTime(String overheadExpensesTime) {
		this.overheadExpensesTime = overheadExpensesTime;
	}

	public String getLogisticsInsuranceTime() {
		return logisticsInsuranceTime;
	}

	public void setLogisticsInsuranceTime(String logisticsInsuranceTime) {
		this.logisticsInsuranceTime = logisticsInsuranceTime;
	}

	public void clone(MappingTableModel model) {
		if (!(model instanceof BusiContainer)) {
			return;
		}
		BusiContainer obj = (BusiContainer) model;
		if (obj != null) {
			obj.busiId = busiId;
			obj.sequence = sequence;
			obj.accreditId = accreditId;
			obj.state = state;
			obj.isLock = isLock;
			obj.checkInFee = checkInFee;
			obj.appointTime = appointTime;
			obj.arriveTime = arriveTime;
			obj.leaveTime = leaveTime;
			obj.returnTime = returnTime;
			obj.factoryId = factoryId;
			obj.factoryShortName = factoryShortName;
			obj.contact = contact;
			obj.telephone = telephone;
			obj.fax = fax;
			obj.mobilePhone = mobilePhone;
			obj.address = address;
			obj.loadPlace = loadPlace;
			obj.factoryId2 = factoryId2;
			obj.factoryShortName2 = factoryShortName2;
			obj.contact2 = contact2;
			obj.telephone2 = telephone2;
			obj.mobilePhone2 = mobilePhone2;
			obj.address2 = address2;
			obj.loadPlace2 = loadPlace2;
			obj.containerType = containerType;
			obj.containerNo = containerNo;
			obj.sealNo = sealNo;
			obj.containerWeight = containerWeight;
			obj.otherBookingNo = otherBookingNo;
			obj.otherContainerNo = otherContainerNo;
			obj.otherSealNo = otherSealNo;
			obj.otherContainerWeight = otherContainerWeight;
			obj.weight = weight;
			obj.supplyTime = supplyTime;
			obj.cutOffTime = cutOffTime;
			obj.isDifferentGet = isDifferentGet;
			obj.isDoubleCon = isDoubleCon;
			obj.getConPile = getConPile;
			obj.getConPlace = getConPlace;
			obj.returnConPile = returnConPile;
			obj.returnConPlace = returnConPlace;
			obj.truckId = truckId;
			obj.transportTeamId = transportTeamId;
			obj.driverId = driverId;
			obj.truckShelfId = truckShelfId;
			obj.stayNight = stayNight;
			obj.cargoSO = cargoSO;
			obj.warehouseId = warehouseId;
			obj.warehouse = warehouse;
			obj.cubage = cubage;
			obj.piece = piece;
			obj.goodName = goodName;
			obj.manageMode = manageMode;
			obj.kilometres = kilometres;
			obj.factOilQuantity = factOilQuantity;
			obj.truck = truck;
			obj.transportTeam = transportTeam;
			obj.driver = driver;
			obj.driverCost = driverCost;
			obj.driverMobilePhone = driverMobilePhone;
			obj.truckShelf = truckShelf;
			obj.shipperRemark = shipperRemark;
			obj.followRemark = followRemark;
			obj.incomeRemark = incomeRemark;
			obj.payoutRemark = payoutRemark;
			obj.customerJobNo = customerJobNo;
			obj.standConsume = standConsume;
			obj.wastageOilKm = wastageOilKm;
			obj.isPrint = isPrint;
			obj.isGetSend = isGetSend;
			obj.isReturnSend = isReturnSend;
			obj.isPortSend = isPortSend;
			obj.greenTightTime = greenTightTime;
			obj.truckState = truckState;
			obj.customsNo = customsNo;
			obj.externalBusiId = externalBusiId;
			obj.isCompose = isCompose;
			obj.composeId = composeId;
			obj.togetherId = togetherId;
			obj.advanceMoney = advanceMoney;
			obj.writeOffMan = writeOffMan;
			obj.writeOffMoney = writeOffMoney;
			obj.writeOffState = writeOffState;
			obj.writeOffTime = writeOffTime;
			obj.overheadExpenses = overheadExpenses;
			obj.auditMan = auditMan;
			obj.auditTime = auditTime;
			obj.endPort = endPort;
			obj.isSendFactory = isSendFactory;
			obj.toNo = toNo;
			obj.joNo = joNo;
			obj.orderNo = orderNo;
			obj.damcoBookingNo = damcoBookingNo;
			obj.tallyClerk = tallyClerk;
			obj.tallyClerkId = tallyClerkId;
			obj.bookingArriveTime = bookingArriveTime;
			obj.shipLeaveTime = shipLeaveTime;
			obj.arriveCustomTime = arriveCustomTime;
			obj.leaveCustomTime = leaveCustomTime;
			obj.arriveFieldTime = arriveFieldTime;
			obj.leaveFieldTime = leaveFieldTime;
			obj.arriveWarehouseTime = arriveWarehouseTime;
			obj.beginLoadTime = beginLoadTime;
			obj.handInDockTime = handInDockTime;
			obj.arriveDockTime = arriveDockTime;
			obj.unloadNo = unloadNo;
			obj.cutOffWarehouseTime = cutOffWarehouseTime;
			obj.svoNo = svoNo;
			obj.arriveGetPileTime = arriveGetPileTime;
			obj.leaveGetPileTime = leaveGetPileTime;
			obj.realPiece = realPiece;
			obj.packingSize = packingSize;
			obj.weightUnit = weightUnit;
			obj.pieceUnit = pieceUnit;
			obj.leaveWarehouseTime = leaveWarehouseTime;
			obj.customsRemark = customsRemark;
			obj.transportTeamRemark = transportTeamRemark;
			obj.shipperRemark = shipRemark;
			obj.tallyClerkRemark = tallyClerkRemark;
			obj.otherRemark = otherRemark;
			obj.customsInRemark = customsInRemark;
			obj.customerInRemark = customerInRemark;
			obj.customerRemark = customerRemark;
			obj.auditState = auditState;
			obj.writeOffRemark = writeOffRemark;
			obj.imageName = imageName;
			obj.isHaveCustoms = isHaveCustoms;
			obj.schedulerNo = schedulerNo;
			obj.specialCaseRemark = specialCaseRemark;
			obj.specialCaseRemarkTime = specialCaseRemarkTime;
			obj.printDate = printDate;
			obj.printCompany = printCompany;
			obj.isPrintBill = isPrintBill;
			obj.isExportPayout = isExportPayout;
			obj.isPressNight = isPressNight;
			obj.logisticsInsurance = logisticsInsurance;
			obj.place = place;
			obj.transportFee = transportFee;
			obj.outOfState = outOfState;
			obj.sendOrderState = sendOrderState;
			obj.driverState = driverState;
			obj.confirmDriverFee = confirmDriverFee;
			obj.isCancel = isCancel;
			obj.dispatchConfirmFee = dispatchConfirmFee;
			obj.yfState = yfState;
			obj.checkDriverFee = checkDriverFee;
			obj.conSealNoRemark = conSealNoRemark;
			obj.checkMan = checkMan;
			obj.checkTime = checkTime;
			obj.checkRemark = checkRemark;
			obj.lng = lng;
			obj.lat = lat;
			obj.advanceTime = advanceTime;
			obj.logisticsInsuranceTime = logisticsInsuranceTime;
			obj.overheadExpensesTime = overheadExpensesTime;
		}
	}

	public void validate() throws DataBaseException {
		if (StringManage.isEmpty(busiId)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"承运单号 不能为空.");
		}
		if (StringManage.isEmpty(containerType)) {
			throw new DataBaseException(DataBaseException.FS_NULL_FIELD_ERROR,
					"柜型 不能为空.");
		}
	}
}