package com.test.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kingsoft.control.Console;
import com.kingsoft.control.cryptography.DES;
import com.test.common.JsonHelper;
import com.test.tcb.vo.JkxBase;
import com.test.tcb.vo.JsonMsg;
import com.test.tcb.vo.TcbBase;
import com.test.tcb.vo.TcbPayoutFee;

public class TestJson {

	public static void main(String[] args) {
		test20160212();
	}

	private static void test20160212() {
		JSONObject json = new JSONObject();
		json.put("account", "招商银行");
		json.put("incomeTotal", 1000);
		json.put("payoutTotal", 800);
		json.put("total", 200);
		System.out.println(json.toString());
	}

	public static void showDES(String a) {
		DES d = new DES();
		System.out.println(d.encrypt(a));
		System.out.println(d.decrypt(a));
	}
	
	private static void test20151207() {
		JkxBase base = new JkxBase();
		base.setAccreditid("tcbzcdtest");
		base.setAppkey("abcdefg");
		base.setMethodname("driverAcceptOrder");
		JSONObject obj = JSONObject.fromObject(base);
		obj.put("truckno", "粤B123");
		obj.put("driver", "张三");
		obj.put("phone", "13500000000");
		String businessid = "Y15120001-1-1";
		String[] businessids = {"Y15120001-1-1"};
		obj.put("businessids", businessids);
		System.out.println("1司机接单:"+obj.toString());

		base.setMethodname("driverRefuseOrder");
		obj = JSONObject.fromObject(base);
		obj.put("businessids", businessids);
		System.out.println("2司机拒单:"+obj.toString());
		
		base.setMethodname("cancelOrder");
		obj = JSONObject.fromObject(base);
		obj.put("businessids", businessids);
		System.out.println("3调度撤单:"+obj.toString());
		
		base.setMethodname("editOrderContainerNo");
		obj = JSONObject.fromObject(base);
		obj.put("businessid", businessid);
		obj.put("containerno", "APLU1111111");
		obj.put("sealno", "f001");
		System.out.println("4修改箱封号:"+obj.toString());
		
		base.setMethodname("synchronizationFees");
		obj = JSONObject.fromObject(base);
		obj.put("username", "金科信");
		obj.put("businessid", businessid);
		JSONObject f = new JSONObject();
		f.put("feeid", 1);
		f.put("amount", 40.5);
		f.put("ispicture", 0);
		JSONArray farr = new JSONArray();
		farr.add(f);
		obj.put("fee", farr);
		System.out.println("5司机同步费用:"+obj.toString());

		base.setMethodname("payment");
		obj = JSONObject.fromObject(base);
		obj.put("username", "金科信");
		f = new JSONObject();
		f.put("businessid", businessid);
		int[] fees = new int[2];
		fees[0] = 100;
		fees[1] = 102;
		f.put("fee", fees);
		farr = new JSONArray();
		farr.add(f);
		obj.put("data", farr);
		System.out.println("6财务付款:"+obj.toString());
		
		base.setMethodname("confirmFee");
		obj = JSONObject.fromObject(base);
		obj.put("businessid", businessid);
		obj.put("type", 2);
		System.out.println("7司机确认费用:"+obj.toString());

		base.setMethodname("confirmAssignment");
		obj = JSONObject.fromObject(base);
		obj.put("businessids", businessids);
		System.out.println("8调度确认出车结束:"+obj.toString());
		
		base.setMethodname("cancelViedOrder");
		obj = JSONObject.fromObject(base);
		obj.put("businessid", businessid);
		obj.put("canceltype", 0);
		System.out.println("9司机撤销抢单:"+obj.toString());
		
		base.setMethodname("manageFee");
		obj = JSONObject.fromObject(base);
		obj.put("beginDate", "2015-12-01");
		obj.put("endDate", "2015-12-31");
		String[] trucks = new String[]{"粤B123"};
		obj.put("trucks", trucks);
		System.out.println("10挂靠费查询:"+obj.toString());
		
		JsonMsg jsonMsg = new JsonMsg();
		jsonMsg.setMsg("success");
		obj = JSONObject.fromObject(jsonMsg);
		System.out.println("返回结果:"+obj.toString());
		
		jsonMsg = new JsonMsg();
		jsonMsg.setRet(2);
		jsonMsg.setMsg("密钥错误");
		obj = JSONObject.fromObject(jsonMsg);
		System.out.println("返回结果:"+obj.toString());
	}

	private static void test20151204() {
		List<ManageFee> list = new ArrayList<ManageFee>();
		ManageFee manageFee = new ManageFee();
		manageFee.setFeeId(1);
		manageFee.setFeeName("费用");
		list.add(manageFee);
		
		JSONArray arr = JSONArray.fromObject(list.toArray(new ManageFee[0]));
		
		JSONObject obj = new JSONObject();
		obj.put("UserName", "Hello");
		obj.put("data", arr);
		obj.put("UserNameId", 40);
		
		System.out.println(arr.toString());
		JSONArray arrLower = JSONtoLowerTools.transArray(arr);
		System.out.println(arrLower.toString());
		System.out.println(obj.toString());
		System.out.println(obj.toString().length());
		JSONObject objLower = JSONtoLowerTools.transObject(obj);
		System.out.println(objLower.toString());
		System.out.println(objLower.toString().length());
	}

	private static void test20151202_2() {
		JsonMsg j = new JsonMsg();
		j.setRet(0);
		j.setMsg("数据已经修改");
		
		String data = "[{\"feeid\":1163,\"amount\":500.0,\"isPicture\":0,\"SyncTime\":\"2015-12-02T09:40:57.667\"}]";
		j.setData("[{\"feeid\":1163,\"amount\":500.0,\"isPicture\":0,\"SyncTime\":\"2015-12-02 09:40:57\"}]");
		String jsonString = JSONObject.fromObject(j).toString();
		System.out.println(jsonString);
		JsonMsg json =(JsonMsg) JSONObject.toBean(JSONObject.fromObject(jsonString),JsonMsg.class);
		//System.out.println(data);
		
		TcbPayoutFee[] datas = (TcbPayoutFee[]) JsonHelper.jsonToObj(data, TcbPayoutFee.class); 
		System.out.println("end");
	}

	private static void test20151202() {
		JSONObject j = new JSONObject();
		j.put("ret", 0);
		j.put("msg", "成功");
		List<TcbPayoutFee> list = new ArrayList<TcbPayoutFee>();
		TcbPayoutFee t = new TcbPayoutFee();
		t.setAmount(100);
		t.setFeeid(1001);
		t.setSynctime(Console.FS_TIME.getNow());
		list.add(t);
		TcbPayoutFee t1 = new TcbPayoutFee();
		t1.setAmount(200);
		t1.setFeeid(1002);
		t1.setSynctime(Console.FS_TIME.getNow());
		list.add(t1);
		j.put("data",JSONArray.fromObject(list).toString());
		System.out.println(j.toString());
		
	}

	private static void test20151028() {
		ManageFeeVO vo = new ManageFeeVO();
		List<ManageFee> list = new ArrayList<ManageFee>();
		ManageFee fee = new ManageFee();
		fee.setFeeId(1);
		fee.setFeeName("预付油卡");
		fee.setMoney(5000);
		fee.setManageId(23);
		list.add(fee);
		fee = new ManageFee();
		fee.setFeeId(3);
		fee.setFeeName("社保");
		fee.setMoney(200);
		fee.setManageId(44);
		list.add(fee);

		vo.setManageFees(list.toArray(new ManageFee[0]));
		vo.setTruck("粤BL3603");
		vo.setMonth("2015-10");
		vo.setOperateManName("管理员");
		vo.setOperateTime("2015-10-28 09:00:00");
		vo.setRemark("10月20号充值油卡5000");

	}
}
