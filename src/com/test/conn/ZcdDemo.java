package com.test.conn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.kingsoft.control.Console;
import com.kingsoft.control.cryptography.DES;
import com.test.conn.zcd.BusiContainer;
import com.test.conn.zcd.BusiPayoutFee;

public class ZcdDemo {
	public static final String url = "jdbc:mysql://sz.cntms.net:3306/zcd?userUnicode=true&characterEncoding=UTF-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "kingsoft";
	public static final String password = "4670af91302f9bea18c695d7d7573a4d";

	public Connection conn = null;
	public PreparedStatement pst = null;
	// public static String busiIds = "'Y15111280','Y15111398','Y15111400','Y15111403','Y15111407','Y15111461','Y15111462','Y15111472','Y15111473','Y15111474','Y15111477','Y15111479','Y15111480','Y15111519','Y15111521','Y15111527','Y15111529','Y15111537','Y15111539','Y15111540','Y15111541','Y15111557','Y15111558','Y15111563','Y15111566','Y15111568','Y15111571','Y15111572','Y15111573','Y15111574','Y15111577','Y15111580','Y15111584','Y15111585','Y15111587','Y15111589','Y15111590','Y15111593','Y15111599','Y15111605','Y15111606','Y15111607','Y15111608','Y15111609','Y15111610','Y15111612','Y15111613','Y15111615','Y15111617','Y15111618','Y15111620','Y15111624','Y15111625','Y15111626','Y15111627','Y15111628','Y15111629','Y15111630','Y15111632','Y15111634','Y15111636','Y15111637','Y15111638','Y15111641','Y15111642','Y15111644','Y15111645','Y15111646','Y15111649','Y15111650','Y15111651','Y15120001','Y15120002','Y15120006','Y15120007','Y15120008','Y15120009','Y15120011','Y15120012','Y15120013','Y15120014','Y15120015','Y15120016','Y15120017','Y15120019','Y15120021','Y15120022','Y15120025','Y15120029','Y15120030','Y15120043','Y15120044','Y15120045','Y15120048','Y15120051','Y15120052','Y15120054','Y15120055','Y15120057','Y15120058','Y15120061','Y15120063','Y15120064','Y15120070','Y15120076','Y15120077','Y15120080','Y15120081','Y15120082','Y15120085','Y15120086','Y15120089','Y15120090','Y15120093','Y15120094','Y15120095','Y15120097','Y15120098','Y15120099','Y15120104','Y15120105','Y15120107','Y15120110','Y15120111','Y15120113','Y15120114','Y15120115','Y15120120','Y15120121','Y15120124','Y15120126','Y15120130','Y15120135','Y15120138','Y15120141','Y15120144','Y15120145','Y15120155','Y15120156','Y15120161','Y15120165','Y15120166','Y15120169','Y15120170','Y15120174','Y15120178','Y15120183','Y15120184','Y15120186','Y15120192','Y15120198','Y15120199','Y15120201','Y15120208','Y15120209','Y15120210','Y15120215','Y15120219','Y15120223','Y15120231','Y15120232','Y15120236','Y15120240','Y15120244','Y15120245','Y15120248','Y15120251','Y15120252','Y15120253','Y15120255','Y15120256','Y15120257','Y15120258','Y15120259','Y15120281','Y15120282','Y15120286','Y15120288','Y15120289','Y15120290','Y15120294','Y15120298','Y15120303','Y15120314','Y15120322','Y15120343','Y15120405','Y15120449','Y15120515','Y15120757','Y15120759','Y15120812','Y15120813','Y15120958','Y15121005','Y15121075','Y15121218','Y15121320','Y15121378','Y15121412','Y15121455','Y15121470','Y15121475','Y15121546','Y15121641','Y15121656','Y15121660','Y15121673','Y15121794'";
	// 多出来的81笔
	public static String busiIds = "'Y15111520','Y15111538','Y15111542','Y15111564','Y15111576','Y15111583','Y15111586','Y15111588','Y15111602','Y15111631','Y15111633','Y15111635','Y15120032','Y15120035','Y15120036','Y15120056','Y15120074','Y15120109','Y15120122','Y15120127','Y15120131','Y15120142','Y15120150','Y15120163','Y15120164','Y15120167','Y15120168','Y15120171','Y15120172','Y15120173','Y15120175','Y15120176','Y15120179','Y15120233','Y15120239','Y15120242','Y15120250','Y15120260','Y15120266','Y15120270','Y15120277','Y15120283','Y15120293','Y15120330','Y15120331','Y15120332','Y15120338','Y15120348','Y15120355','Y15120357','Y15120358','Y15120359','Y15120365','Y15120366','Y15120367','Y15120368','Y15120369','Y15120379','Y15120385','Y15120387','Y15120456','Y15120650','Y15120694','Y15120940','Y15121045','Y15121049','Y15121171','Y15121195','Y15121267','Y15121427','Y15121589','Y15121590','Y15121602','Y15121617','Y15121649','Y15121650','Y15121709','Y15121714','Y15121734','Y15121755','Y15121765'";

	// public static String busiIds = "'Y15111280'";

	public static void main(String[] args) {
		// T20160107();
		T20160107_2();
		System.out.println("end");
	}

	private static void T20160107_3() {
		ZcdDemo zcdDemo = new ZcdDemo();
		try {
			// String sql = "SELECT busi_container.busi_id,busi_container.sequence,busi_container.overhead_expenses,busi_container.advance_money,busi_container.logistics_insurance,SUM(busi_payout_fee.money) AS money FROM busi_container LEFT JOIN busi_payout_fee ON busi_container.accredit_id=busi_payout_fee.accredit_id AND busi_container.busi_id=busi_payout_fee.busi_id AND busi_container.sequence=busi_payout_fee.sequence AND (busi_payout_fee.payout_to_unit_id='truck' OR busi_payout_fee.payout_to_unit_id='transport_team' OR busi_payout_fee.payout_to_unit_id='driver') WHERE busi_container.accredit_id='zcd' AND (busi_container.out_of_state=1 OR busi_container.driver_state=1) AND busi_container.appoint_time >='2015-12-01 00:00:00' AND busi_container.appoint_time <='2015-12-31 23:59:59' AND busi_container.state != 8 GROUP BY busi_container.busi_id,busi_container.sequence";// SQL语句
			String sql = "SELECT busi_container.busi_id,busi_container.sequence,busi_container.overhead_expenses,busi_container.advance_money,busi_container.logistics_insurance,SUM(busi_payout_fee.money) AS money FROM busi_container LEFT JOIN busi_payout_fee ON busi_container.accredit_id=busi_payout_fee.accredit_id AND busi_container.busi_id=busi_payout_fee.busi_id AND busi_container.sequence=busi_payout_fee.sequence AND (busi_payout_fee.payout_to_unit_id='truck' OR busi_payout_fee.payout_to_unit_id='transport_team' OR busi_payout_fee.payout_to_unit_id='driver') WHERE busi_container.accredit_id='zcd' AND busi_container.busi_id IN (" + busiIds + ") AND busi_container.appoint_time >='2015-10-01 00:00:00' AND busi_container.appoint_time <='2015-12-31 23:59:59' AND busi_container.state != 8 GROUP BY busi_container.busi_id,busi_container.sequence";// SQL语句
			zcdDemo.select(sql);
			BusiContainer[] cons = putConEntity(zcdDemo.pst.executeQuery());
			List<List<Object>> datas = mergeConFee(cons);
			zcdDemo.exportExcel(datas);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zcdDemo.close();
		}

	}

	private static List<List<Object>> mergeConFee(BusiContainer[] cons) {
		List<List<Object>> datas = new ArrayList<List<Object>>();// 数据集合
		List<Object> head = new ArrayList<Object>();
		head.add("运单号");
		head.add("司机费用合计");
		head.add("预付费");
		head.add("扣除管理费");
		head.add("物流责任险");
		head.add("扣除特殊费用后小计");
		datas.add(head);// 添加表头

		List<Object> data;
		for (BusiContainer con : cons) {
			data = new ArrayList<Object>();
			data.add("\'" + con.getBusiId() + "-" + con.getSequence() + "-1\'");
			data.add(con.getWeight());
			data.add(con.getAdvanceMoney());
			data.add(con.getOverheadExpenses());
			data.add(con.getLogisticsInsurance());
			data.add(Console.FS_NUMBER.subtract(con.getWeight(), con.getAdvanceMoney() + con.getOverheadExpenses() + con.getLogisticsInsurance(), 2));
			datas.add(data);
		}
		return datas;
	}

	private static BusiContainer[] putConEntity(ResultSet rs) throws SQLException {
		BusiContainer[] rvalue = new BusiContainer[0];
		ArrayList<BusiContainer> list = null;
		BusiContainer busiContainer;
		while (rs.next()) {
			busiContainer = new BusiContainer();
			busiContainer.setBusiId(rs.getString("busi_id"));
			busiContainer.setSequence(rs.getInt("sequence"));
			busiContainer.setOverheadExpenses(rs.getDouble("overhead_expenses"));
			busiContainer.setLogisticsInsurance(rs.getDouble("logistics_insurance"));
			busiContainer.setAdvanceMoney(rs.getDouble("advance_money"));
			busiContainer.setWeight(rs.getDouble("money"));

			if (list == null) {
				list = new ArrayList<BusiContainer>();
			}
			list.add(busiContainer);
		}
		if (list != null) {
			rvalue = (BusiContainer[]) list.toArray(rvalue);
		}
		if (rs != null) {
			rs.close();
		}
		return rvalue;
	}

	// 统计9到11月特殊费用
	private static void T20160107_2() {
		ZcdDemo zcdDemo = new ZcdDemo();
		try {
			// String sql = "select * FROM busi_container WHERE busi_container.accredit_id='zcd' AND (busi_container.out_of_state=1 OR busi_container.driver_state=1) AND busi_container.appoint_time >='2015-12-01 00:00:00' AND busi_container.appoint_time <='2015-12-31 23:59:59' AND busi_container.state != 8 GROUP BY busi_container.busi_id,busi_container.sequence";// SQL语句
			// String sql = "select * FROM busi_container WHERE accredit_id='zcd' AND busi_container.appoint_time >='2015-09-01 00:00:00' AND busi_container.state != 8 AND busi_id IN (" + busiIds + ")";// SQL语句
			String sql = "select * FROM busi_container WHERE accredit_id='zcd' AND busi_container.appoint_time >='2015-09-01 00:00:00' AND busi_container.state != 8 AND busi_container.appoint_time<='2015-11-30 23:59:59' AND busi_container.send_order_state>0";// SQL语句
			zcdDemo.select(sql);
			BusiContainer[] cons = putEntity(zcdDemo.pst.executeQuery());
			List<List<Object>> datas = mergeCon(cons);
			zcdDemo.exportExcel(datas);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zcdDemo.close();
		}
	}

	/**
	 * 统计12月单的特殊费用
	 * 
	 * @param cons
	 * @return
	 */
	private static List<List<Object>> mergeCon(BusiContainer[] cons) {
		List<List<Object>> datas = new ArrayList<List<Object>>();// 数据集合
		List<Object> head = new ArrayList<Object>();
		head.add("运单号");
		head.add("费用ID");
		head.add("费用名称");
		head.add("金额");
		head.add("同步时间");
		datas.add(head);// 添加表头

		List<Object> data;
		for (BusiContainer con : cons) {
			if (con.getAdvanceMoney() != 0) {
				data = new ArrayList<Object>();
				data.add("\'" + con.getBusiId() + "-" + con.getSequence() + "-1\'");
				data.add("\'90001\'");
				data.add("\'预付费\'");
				data.add(-con.getAdvanceMoney());
				data.add("\'" + con.getAdvanceTime() + "\'");
				datas.add(data);
			}
			if (con.getOverheadExpenses() != 0) {
				data = new ArrayList<Object>();
				data.add("\'" + con.getBusiId() + "-" + con.getSequence() + "-1\'");
				data.add("\'90002\'");
				data.add("\'扣除管理费\'");
				data.add(-con.getOverheadExpenses());
				data.add("\'" + con.getOverheadExpensesTime() + "\'");
				datas.add(data);
			}
			if (con.getLogisticsInsurance() != 0) {
				data = new ArrayList<Object>();
				data.add("\'" + con.getBusiId() + "-" + con.getSequence() + "-1\'");
				data.add("\'90003\'");
				data.add("\'物流责任险\'");
				data.add(-con.getLogisticsInsurance());
				data.add("\'" + con.getLogisticsInsuranceTime() + "\'");
				datas.add(data);
			}
		}
		return datas;
	}

	// 查询费用信息
	private static void T20160107() {
		ZcdDemo zcdDemo = new ZcdDemo();
		try {
			// String sql = "select * FROM busi_payout_fee WHERE accredit_id='zcd' AND busi_id IN (" + busiIds + ")";
			// String sql = "select busi_payout_fee.* FROM busi_payout_fee INNER JOIN busi_container ON busi_container.accredit_id=busi_payout_fee.accredit_id AND busi_container.busi_id=busi_payout_fee.busi_id AND busi_container.sequence=busi_payout_fee.sequence WHERE busi_payout_fee.accredit_id='zcd' AND busi_container.state !=8 AND busi_container.appoint_time>='2015-10-01 00:00:00' AND busi_payout_fee.busi_id IN (" + busiIds + ")";
			String sql = "select busi_payout_fee.* FROM busi_payout_fee INNER JOIN busi_container ON busi_container.accredit_id=busi_payout_fee.accredit_id AND busi_container.busi_id=busi_payout_fee.busi_id AND busi_container.sequence=busi_payout_fee.sequence WHERE busi_payout_fee.accredit_id='zcd' AND busi_container.state !=8 AND busi_container.send_order_state>0 AND busi_payout_fee.money!=0 AND busi_container.appoint_time>='2015-09-01 00:00:00' AND busi_container.appoint_time<='2015-11-30 23:59:59'";
			zcdDemo.select(sql);
			BusiPayoutFee[] fees = putFeeEntity(zcdDemo.pst.executeQuery());
			List<List<Object>> datas = mergeFee(fees);
			zcdDemo.exportExcel(datas);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zcdDemo.close();
		}
	}

	private static void T20160106() {
		ZcdDemo zcdDemo = new ZcdDemo();
		try {
			String sql = "select * FROM busi_container WHERE accredit_id='zcd' AND busi_container.appoint_time >='2015-09-01 00:00:00' AND busi_id IN (" + busiIds + ")";// SQL语句
			zcdDemo.select(sql);
			BusiContainer[] cons = putEntity(zcdDemo.pst.executeQuery());
			sql = "select * FROM busi_payout_fee WHERE accredit_id='zcd' AND busi_id IN (" + busiIds + ")";
			zcdDemo.select(sql);
			BusiPayoutFee[] fees = putFeeEntity(zcdDemo.pst.executeQuery());
			List<List<Object>> datas = merge(cons, fees);
			zcdDemo.exportExcel(datas);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zcdDemo.close();
		}
	}

	private static List<List<Object>> merge(BusiContainer[] cons, BusiPayoutFee[] fees) {
		HashMap<String, BusiContainer> conMap = new HashMap<String, BusiContainer>();
		String key;
		for (BusiContainer con : cons) {
			key = con.getBusiId() + "-" + con.getSequence();
			conMap.put(key, con);
		}
		HashMap<String, HashMap<Integer, BusiPayoutFee>> feeMap = new HashMap<String, HashMap<Integer, BusiPayoutFee>>();
		HashMap<Integer, BusiPayoutFee> feeList;
		LinkedHashMap<Integer, BusiPayoutFee> nameMap = new LinkedHashMap<Integer, BusiPayoutFee>();
		for (BusiPayoutFee fee : fees) {
			// 不是司机费用，过滤
			if (!fee.getPayoutToUnitId().equals("truck") && !fee.getPayoutToUnitId().equals("driver") && !fee.getPayoutToUnitId().equals("transport_team")) {
				continue;
			}
			// 金额为0，过滤
			if (fee.getMoney() == 0) {
				continue;
			}
			key = fee.getBusiId() + "-" + fee.getSequence();
			if (feeMap.containsKey(key)) {
				feeList = feeMap.get(key);
			} else {
				feeList = new HashMap<Integer, BusiPayoutFee>();
			}
			feeList.put(fee.getFeeId(), fee);
			feeMap.put(key, feeList);
			if (!nameMap.containsKey(fee.getFeeName())) {
				nameMap.put(fee.getFeeId(), fee);
			}
		}

		List<List<Object>> datas = new ArrayList<List<Object>>();// 数据集合
		List<Object> head = new ArrayList<Object>();
		head.add("承运单号");
		head.add("当前状态");
		head.add("出车状态");
		head.add("派单状态");
		head.add("司机状态");
		head.add("司机确认费用状态");
		head.add("是否撤单");
		head.add("调度确认费用状态");
		head.add("管理费");
		head.add("预付费");
		head.add("物流责任险");
		for (Integer feeId : nameMap.keySet()) {
			head.add(nameMap.get(feeId).getFeeName());
		}
		datas.add(head);// 添加表头

		List<Object> data;
		for (BusiContainer con : cons) {
			data = new ArrayList<Object>();
			key = con.getBusiId() + "-" + con.getSequence();
			if (feeMap.containsKey(key)) {
				feeList = feeMap.get(key);
			} else {
				feeList = new HashMap<Integer, BusiPayoutFee>();
			}
			data.add(con.getBusiId());
			switch (con.getState()) {
			case -1:
				data.add("接单");
				break;
			case 0:
				data.add("待派车");
				break;
			case 1:
				data.add("已派车");
				break;
			case 3:
				data.add("待录费用");
				break;
			case 4:
				data.add("已录费用");
				break;
			case 5:
				data.add("审核通过");
				break;
			case 6:
				data.add("取消");
				break;
			case 7:
				data.add("待审核");
				break;
			case 8:
				data.add("作废");
				break;
			default:
				data.add("");
				break;
			}
			if (con.getOutOfState() == 0) {
				data.add("出车未结束");
			} else if (con.getOutOfState() == 1) {
				data.add("出车结束");
			} else {
				data.add("");
			}
			if (con.getSendOrderState() == 0) {
				data.add("初始");
			} else if (con.getSendOrderState() == 1) {
				data.add("预抢单");
			} else if (con.getSendOrderState() == 2) {
				data.add("预派单");
			} else {
				data.add("");
			}
			if (con.getDriverState() == 0) {
				data.add("初始");
			} else if (con.getDriverState() == 1) {
				data.add("司机接单");
			} else if (con.getDriverState() == 2) {
				data.add("司机拒单");
			} else if (con.getDriverState() == 3) {
				data.add("无人抢单");
			} else {
				data.add("");
			}
			if (con.getConfirmDriverFee() == 0) {
				data.add("未确认");
			} else if (con.getConfirmDriverFee() == 1) {
				data.add("已确认");
			} else {
				data.add("");
			}
			if (con.getIsCancel() == 0) {
				data.add("否");
			} else if (con.getIsCancel() == 1) {
				data.add("是");
			} else {
				data.add("");
			}
			if (con.getDispatchConfirmFee() == 0) {
				data.add("未确认");
			} else if (con.getDispatchConfirmFee() == 1) {
				data.add("已确认");
			} else {
				data.add("");
			}
			data.add(con.getOverheadExpenses());
			data.add(con.getAdvanceMoney());
			data.add(con.getLogisticsInsurance());
			for (Integer feeId : nameMap.keySet()) {
				if (feeList.containsKey(feeId)) {
					data.add(feeList.get(feeId).getMoney());
				} else {
					data.add("");
				}
			}
			datas.add(data);
		}

		for (List<Object> list : datas) {
			for (Object o : list) {
				System.out.print(o + " , ");
			}
			System.out.println("");
		}
		System.out.println("end");
		return datas;
	}

	private static List<List<Object>> mergeFee(BusiPayoutFee[] fees) {
		List<List<Object>> datas = new ArrayList<List<Object>>();// 数据集合
		List<Object> head = new ArrayList<Object>();
		head.add("运单号");
		head.add("费用ID");
		head.add("费用名称");
		head.add("金额");
		head.add("同步时间");
		datas.add(head);// 添加表头

		List<Object> data;
		for (BusiPayoutFee fee : fees) {
			// 不是司机费用，过滤
			if (!fee.getPayoutToUnitId().equals("truck") && !fee.getPayoutToUnitId().equals("driver") && !fee.getPayoutToUnitId().equals("transport_team")) {
				continue;
			}
			// 金额为0，过滤
			if (fee.getMoney() == 0) {
				continue;
			}
			data = new ArrayList<Object>();
			data.add("\'" + fee.getBusiId() + "-" + fee.getSequence() + "-1\'");
			data.add("\'" + fee.getFeeId() + "\'");
			data.add("\'" + fee.getFeeName() + "\'");
			data.add(fee.getMoney());
			data.add("\'" + fee.getSyncTime() + "\'");
			datas.add(data);
		}
		return datas;
	}

	public void exportExcel(List<List<Object>> datas) throws Exception {
		if (datas.size() == 0) {
			return;
		}
		String path = "C:\\Users\\lyq\\Desktop\\zcd.xls";
		File file = new File(path);
		WritableWorkbook wb = Workbook.createWorkbook(file);
		WritableSheet sheet = wb.createSheet("Data ", 0);
		sheet.setPageSetup(PageOrientation.LANDSCAPE, PaperSize.A4, 0.5d, 0.5d);

		// 设置表头宽度
		for (int i = 0; i < datas.get(0).size(); i++) {
			sheet.setColumnView(i, 10);
		}

		// 设置居中样式
		WritableCellFormat center = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false));
		center.setBorder(Border.ALL, BorderLineStyle.THIN);
		center.setAlignment(Alignment.CENTRE);
		center.setVerticalAlignment(VerticalAlignment.CENTRE);
		center.setWrap(true);

		// 设置居左样式
		WritableCellFormat left = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false));
		left.setBorder(Border.ALL, BorderLineStyle.THIN);
		left.setAlignment(Alignment.LEFT);
		left.setVerticalAlignment(VerticalAlignment.CENTRE);
		left.setWrap(true);

		// 设置居右样式
		WritableCellFormat right = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false));
		right.setBorder(Border.ALL, BorderLineStyle.THIN);
		right.setAlignment(Alignment.RIGHT);
		right.setVerticalAlignment(VerticalAlignment.CENTRE);
		right.setWrap(true);

		// 设置表格标头
		int len = 0;
		int rows = 0;
		for (List<Object> data : datas) {
			len = 0;
			for (Object o : data) {
				if (o.toString().matches("\\d+\\.?\\d*")) {
					sheet.addCell(new jxl.write.Number(len, rows, new Double(o.toString()), right));
				} else {
					sheet.addCell(new Label(len, rows, o.toString(), right));
				}
				len++;
			}
			sheet.setRowView(rows, 400);
			rows++;
		}
		wb.write();
		wb.close();
	}

	private static final BusiContainer[] putEntity(ResultSet rs) throws SQLException {
		BusiContainer[] rvalue = new BusiContainer[0];
		ArrayList<BusiContainer> list = null;
		BusiContainer busiContainer;
		while (rs.next()) {
			busiContainer = new BusiContainer();
			putEntity(rs, busiContainer);

			if (list == null) {
				list = new ArrayList<BusiContainer>();
			}
			list.add(busiContainer);
		}
		if (list != null) {
			rvalue = (BusiContainer[]) list.toArray(rvalue);
		}
		if (rs != null) {
			rs.close();
		}
		return rvalue;
	}

	private static void putEntity(ResultSet rs, BusiContainer busiContainer) throws SQLException {
		busiContainer.setBusiId(rs.getString("busi_id"));
		busiContainer.setSequence(rs.getInt("sequence"));
		busiContainer.setAccreditId(rs.getString("accredit_id"));
		busiContainer.setCheckInFee(rs.getInt("check_in_fee"));
		busiContainer.setState(rs.getInt("state"));
		busiContainer.setIsLock(rs.getInt("is_lock"));
		busiContainer.setAppointTime(rs.getString("appoint_time"));
		busiContainer.setArriveTime(rs.getString("arrive_time"));
		busiContainer.setLeaveTime(rs.getString("leave_time"));
		busiContainer.setFactoryId(rs.getInt("factory_id"));
		busiContainer.setFactoryShortName(rs.getString("factory_short_name"));
		busiContainer.setContact(rs.getString("contact"));
		busiContainer.setTelephone(rs.getString("telephone"));
		busiContainer.setFax(rs.getString("fax"));
		busiContainer.setMobilePhone(rs.getString("mobile_phone"));
		busiContainer.setAddress(rs.getString("address"));
		busiContainer.setLoadPlace(rs.getString("load_place"));
		busiContainer.setFactoryId2(rs.getInt("factory_id2"));
		busiContainer.setFactoryShortName2(rs.getString("factory_short_name2"));
		busiContainer.setContact2(rs.getString("contact2"));
		busiContainer.setTelephone2(rs.getString("telephone2"));
		busiContainer.setMobilePhone2(rs.getString("mobile_phone2"));
		busiContainer.setAddress2(rs.getString("address2"));
		busiContainer.setLoadPlace2(rs.getString("load_place2"));
		busiContainer.setContainerType(rs.getString("container_type"));
		busiContainer.setContainerNo(rs.getString("container_no"));
		busiContainer.setSealNo(rs.getString("seal_no"));
		busiContainer.setContainerWeight(rs.getDouble("container_weight"));
		busiContainer.setOtherBookingNo(rs.getString("other_booking_no"));
		busiContainer.setOtherContainerNo(rs.getString("other_container_no"));
		busiContainer.setOtherSealNo(rs.getString("other_seal_no"));
		busiContainer.setOtherContainerWeight(rs.getDouble("other_container_weight"));
		busiContainer.setWeight(rs.getDouble("weight"));
		busiContainer.setSupplyTime(rs.getString("supply_time"));
		busiContainer.setCutOffTime(rs.getString("cut_off_time"));
		busiContainer.setReturnTime(rs.getString("return_time"));
		busiContainer.setIsDifferentGet(rs.getInt("is_different_get"));
		busiContainer.setIsDoubleCon(rs.getInt("is_double_con"));
		busiContainer.setGetConPile(rs.getString("get_con_pile"));
		busiContainer.setGetConPlace(rs.getString("get_con_place"));
		busiContainer.setReturnConPile(rs.getString("return_con_pile"));
		busiContainer.setReturnConPlace(rs.getString("return_con_place"));
		busiContainer.setTruck(rs.getString("truck"));
		busiContainer.setTruckId(rs.getInt("truck_id"));
		busiContainer.setTransportTeam(rs.getString("transport_team"));
		busiContainer.setTransportTeamId(rs.getInt("transport_team_id"));
		busiContainer.setDriver(rs.getString("driver"));
		busiContainer.setDriverId(rs.getInt("driver_id"));
		busiContainer.setTruckShelf(rs.getString("truck_shelf"));
		busiContainer.setTruckShelfId(rs.getInt("truck_shelf_id"));
		busiContainer.setStayNight(rs.getInt("stay_night"));
		busiContainer.setCargoSO(rs.getString("cargo_so"));
		busiContainer.setWarehouseId(rs.getInt("warehouse_id"));
		busiContainer.setWarehouse(rs.getString("warehouse"));
		busiContainer.setCubage(rs.getDouble("cubage"));
		busiContainer.setPiece(rs.getInt("piece"));
		busiContainer.setManageMode(rs.getInt("manage_mode"));
		busiContainer.setKilometres(rs.getDouble("kilometres"));
		busiContainer.setFactOilQuantity(rs.getDouble("fact_oil_quantity"));
		busiContainer.setGoodName(rs.getString("good_name"));
		busiContainer.setShipperRemark(rs.getString("shipper_remark"));
		busiContainer.setFollowRemark(rs.getString("follow_remark"));
		busiContainer.setIncomeRemark(rs.getString("income_remark"));
		busiContainer.setPayoutRemark(rs.getString("payout_remark"));
		busiContainer.setDriverCost(rs.getDouble("driver_cost"));
		busiContainer.setDriverMobilePhone(rs.getString("driver_mobile_phone"));
		busiContainer.setCustomerJobNo(rs.getString("customer_job_no"));
		busiContainer.setStandConsume(rs.getDouble("stand_consume"));
		busiContainer.setWastageOilKm(rs.getDouble("wastage_oil_km"));
		busiContainer.setIsReturnSend(rs.getInt("is_return_send"));
		busiContainer.setIsGetSend(rs.getInt("is_get_send"));
		busiContainer.setIsPortSend(rs.getInt("is_port_send"));
		busiContainer.setGreenTightTime(rs.getString("green_light_time"));
		busiContainer.setTruckState(rs.getString("truck_state"));
		busiContainer.setCustomsNo(rs.getString("customs_no"));
		busiContainer.setExternalBusiId(rs.getString("external_busi_id"));
		busiContainer.setIsCompose(rs.getInt("is_compose"));
		busiContainer.setComposeId(rs.getString("compose_id"));
		busiContainer.setTogetherId(rs.getString("together_id"));
		busiContainer.setAdvanceMoney(rs.getDouble("advance_money"));
		busiContainer.setWriteOffMoney(rs.getDouble("write_off_money"));
		busiContainer.setWriteOffMan(rs.getInt("write_off_man"));
		busiContainer.setWriteOffTime(rs.getString("write_off_time"));
		busiContainer.setWriteOffState(rs.getInt("write_off_state"));
		busiContainer.setOverheadExpenses(rs.getDouble("overhead_expenses"));
		busiContainer.setEndPort(rs.getString("end_port"));
		busiContainer.setToNo(rs.getString("to_no"));
		busiContainer.setJoNo(rs.getString("jo_no"));
		busiContainer.setOrderNo(rs.getString("order_no"));
		busiContainer.setDamcoBookingNo(rs.getString("damco_booking_no"));
		busiContainer.setTallyClerk(rs.getString("tally_clerk"));
		busiContainer.setTallyClerkId(rs.getInt("tally_clerk_id"));
		busiContainer.setBookingArriveTime(rs.getString("booking_arrive_time"));
		busiContainer.setShipLeaveTime(rs.getString("ship_leave_time"));
		busiContainer.setArriveCustomTime(rs.getString("arrive_custom_time"));
		busiContainer.setLeaveCustomTime(rs.getString("leave_custom_time"));
		busiContainer.setArriveFieldTime(rs.getString("arrive_field_time"));
		busiContainer.setLeaveFieldTime(rs.getString("leave_field_time"));
		busiContainer.setArriveWarehouseTime(rs.getString("arrive_warehouse_time"));
		busiContainer.setBeginLoadTime(rs.getString("begin_load_time"));
		busiContainer.setHandInDockTime(rs.getString("hand_in_dock_time"));
		busiContainer.setArriveDockTime(rs.getString("arrive_dock_time"));
		busiContainer.setUnloadNo(rs.getString("unload_no"));
		busiContainer.setCutOffWarehouseTime(rs.getString("cut_off_warehouse_time"));
		busiContainer.setSvoNo(rs.getString("svo_no"));
		busiContainer.setArriveGetPileTime(rs.getString("arrive_get_pile_time"));
		busiContainer.setLeaveGetPileTime(rs.getString("leave_get_pile_time"));
		busiContainer.setRealPiece(rs.getInt("real_piece"));
		busiContainer.setPackingSize(rs.getString("packing_size"));
		busiContainer.setWeightUnit(rs.getInt("weight_unit"));
		busiContainer.setPieceUnit(rs.getInt("piece_unit"));
		busiContainer.setLeaveWarehouseTime(rs.getString("leave_warehouse_time"));
		busiContainer.setCustomsInRemark(rs.getString("customs_in_remark"));
		busiContainer.setCustomerInRemark(rs.getString("customer_in_remark"));
		busiContainer.setCustomsRemark(rs.getString("customs_remark"));
		busiContainer.setTransportTeamRemark(rs.getString("transport_team_remark"));
		busiContainer.setShipRemark(rs.getString("ship_remark"));
		busiContainer.setTallyClerkRemark(rs.getString("tally_clerk_remark"));
		busiContainer.setOtherRemark(rs.getString("other_remark"));
		busiContainer.setCustomerRemark(rs.getString("customer_remark"));
		busiContainer.setAuditState(rs.getInt("audit_state"));
		busiContainer.setWriteOffRemark(rs.getString("write_off_remark"));
		busiContainer.setImageName(rs.getString("image_name"));
		busiContainer.setSchedulerNo(rs.getString("scheduler_no"));
		busiContainer.setSpecialCaseRemark(rs.getString("special_case_remark"));
		busiContainer.setSpecialCaseRemarkTime(rs.getString("special_case_remark_time"));
		busiContainer.setIsOrderContainer(rs.getInt("is_order_container"));
		busiContainer.setPrintDate(rs.getString("print_date"));
		busiContainer.setPrintCompany(rs.getString("print_company"));
		busiContainer.setIsPrintBill(rs.getInt("is_print_bill"));
		busiContainer.setIsPressNight(rs.getInt("is_press_night"));
		busiContainer.setLogisticsInsurance(rs.getDouble("logistics_insurance"));
		busiContainer.setTransportFee(rs.getDouble("transport_fee"));
		busiContainer.setOutOfState(rs.getInt("out_of_state"));
		busiContainer.setSendOrderState(rs.getInt("send_order_state"));
		busiContainer.setDriverState(rs.getInt("driver_state"));
		busiContainer.setConfirmDriverFee(rs.getInt("confirm_driver_fee"));
		busiContainer.setIsCancel(rs.getInt("is_cancel"));
		busiContainer.setDispatchConfirmFee(rs.getInt("dispatch_confirm_fee"));
		busiContainer.setYfState(rs.getInt("yf_state"));
		busiContainer.setPlace(rs.getString("place"));
		busiContainer.setCheckDriverFee(rs.getInt("check_driver_fee"));
		busiContainer.setConSealNoRemark(rs.getString("con_seal_no_remark"));
		busiContainer.setCheckMan(rs.getInt("check_man"));
		busiContainer.setCheckTime(rs.getString("check_time"));
		busiContainer.setCheckRemark(rs.getString("check_remark"));
		busiContainer.setLng(rs.getDouble("lng"));
		busiContainer.setLat(rs.getDouble("lat"));
		busiContainer.setAdvanceTime(rs.getString("advance_time"));
		busiContainer.setOverheadExpensesTime(rs.getString("overhead_expenses_time"));
		busiContainer.setLogisticsInsuranceTime(rs.getString("logistics_insurance_time"));

	}

	private static final BusiPayoutFee[] putFeeEntity(ResultSet rs) throws SQLException {
		BusiPayoutFee[] rvalue = new BusiPayoutFee[0];
		ArrayList<BusiPayoutFee> list = null;
		BusiPayoutFee busiPayoutFee;
		while (rs.next()) {
			busiPayoutFee = new BusiPayoutFee();
			busiPayoutFee.setBusiId(rs.getString("busi_id"));
			busiPayoutFee.setSequence(rs.getInt("sequence"));
			busiPayoutFee.setFeeId(rs.getInt("fee_id"));
			busiPayoutFee.setFeeName(rs.getString("fee_name"));
			busiPayoutFee.setMoney(rs.getDouble("money"));
			busiPayoutFee.setOperator(rs.getInt("operator"));
			busiPayoutFee.setOperateTime(rs.getString("operate_time"));
			busiPayoutFee.setPayoutToUnitId(rs.getString("payout_to_unit_id"));
			busiPayoutFee.setPayoutToObject(rs.getString("payout_to_object"));
			busiPayoutFee.setState(rs.getInt("state"));
			busiPayoutFee.setApplyId(rs.getString("apply_id"));
			busiPayoutFee.setIsAppFee(rs.getInt("is_app_fee"));
			busiPayoutFee.setIsPicture(rs.getInt("is_picture"));
			busiPayoutFee.setOperatorName(rs.getString("operator_name"));
			busiPayoutFee.setIsAskTcb(rs.getInt("is_ask_tcb"));
			busiPayoutFee.setSyncTime(rs.getString("sync_time"));

			if (list == null) {
				list = new ArrayList<BusiPayoutFee>();
			}
			list.add(busiPayoutFee);
		}
		if (list != null) {
			rvalue = (BusiPayoutFee[]) list.toArray(rvalue);
		}
		return rvalue;
	}

	public void select(String sql) {
		try {
			getConn();
			pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getConn() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName(name);// 指定连接类型
			DES d = new DES();
			conn = DriverManager.getConnection(url, user, d.decrypt(password));// 获取连接
		}
	}

	public void close() {
		try {
			if (conn != null) {
				this.conn.close();
				this.conn = null;
			}
			if (pst != null) {
				this.pst.close();
				this.pst = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
