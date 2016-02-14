package com.test.tcb;

import java.util.ArrayList;

import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.kingsoft.control.util.StringManage;
import com.test.tcb.vo.DriverAccept;
import com.test.tcb.vo.DriverFee;
import com.test.tcb.vo.DriverFeeVO;

/**
 * 
 * @author wmy
 * 
 * @version 2015-8-27
 * 
 * @since JDK 1.6
 * 
 */
public class TestZcdTcb {
	private static String S_namespace = "http://www.szyt.net";
	private static String www_tcb = "http://szyt.net:8180/zcd/services/TuoCheBaoService";
	private static String accreditId = "zcd111";
	private static String busiId = "Y15110004-1-1";
//	private static String appkey = "52db4c7e86c81bad";
	private static String appkey = "4670af91302f9beadb49a802dbb0dcaa";
	private static String FS_DriverAcceptOrder = "driverAcceptOrder";// 司机接单接口
	private static String FS_DriverRefuseOrder = "driverRefuseOrder";// 司机拒单接口
	private static String FS_CancelOrder = "cancelOrder";// 调度撤单接口
	private static String FS_EditOrderContainerNo = "editOrderContainerNo";// 修改箱封号接口
	private static String FS_ConfirmAssignment = "confirmAssignment";// 调度确认出车结束接口
	private static String FS_ConfirmFee = "confirmFee";// 司机确认费用接口
	private static String FS_SynchronizationFees = "synchronizationFees";// 同步录入的司机费用接口
	private static String FS_Payment = "payment";// 财务付款接口
	private static String FS_CancelViedOrder = "cancelViedOrder";// 撤销抢单接口
	private static String FS_ManageFee = "manageFee";// 司机挂靠费接口

	public static void main(String[] args) throws Exception {
//		testFS_DriverAcceptOrder();// 接单
//		testFS_DriverRefuseOrder();// 拒单
		testFS_CancelOrder();// 撤单
		testFS_EditOrderContainerNo();// 修改箱封号
//		testFS_SynchronizationFees();// 同步费用
	}
	private static void testFS_ConfirmAssignment() throws Exception {
		DriverAccept driverAccept = new DriverAccept();
		driverAccept.setAccreditid(accreditId);
		driverAccept.setAppkey(appkey);
		driverAccept.setUserid(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add(busiId);
		driverAccept.setBusinessids(list.toArray(new String[0]));
		JSONObject json = JSONObject.fromObject(driverAccept);
		json.put("methodname", FS_ConfirmAssignment);
		System.out.println(json.toString());
		Object[] arguments = new Object[] { json.toString()};
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}
	/**
	 * 测试接单接口
	 * 
	 * @throws Exception
	 */
	private static void testFS_DriverAcceptOrder() throws Exception {
		DriverAccept driverAccept = new DriverAccept();
		driverAccept.setAccreditid(accreditId);
		driverAccept.setAppkey(appkey);
		driverAccept.setUserid(809);
		driverAccept.setTruckno("粤BBB005");
		driverAccept.setDriver("李书良1");
		driverAccept.setPhone("13823698495");
		ArrayList<String> list = new ArrayList<String>();
		list.add(busiId);
		driverAccept.setBusinessids(list.toArray(new String[0]));
		System.out.println(JSONObject.fromObject(driverAccept).toString());
		JSONObject json = JSONObject.fromObject(driverAccept);
		json.put("methodname", FS_DriverAcceptOrder);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}

	/**
	 * 测试拒单接口
	 * 
	 * @throws Exception
	 */
	private static void testFS_DriverRefuseOrder() throws Exception {
		DriverAccept driverAccept = new DriverAccept();
		driverAccept.setAccreditid(accreditId);
		driverAccept.setAppkey(appkey);
		driverAccept.setUserid(809);
		driverAccept.setTruckno("粤BBB005");
		driverAccept.setDriver("李书良1");
		driverAccept.setPhone("13823698495");
		ArrayList<String> list = new ArrayList<String>();
		list.add(busiId);
		driverAccept.setBusinessids(list.toArray(new String[0]));
		System.out.println(JSONObject.fromObject(driverAccept).toString());
		JSONObject json = JSONObject.fromObject(driverAccept);
		json.put("methodname", FS_DriverRefuseOrder);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}
	
	/**
	 * 测试撤单接口
	 * 
	 * @throws Exception
	 */
	private static void testFS_CancelOrder() throws Exception {
		DriverAccept driverAccept = new DriverAccept();
		driverAccept.setAccreditid(accreditId);
		driverAccept.setAppkey(appkey);
		driverAccept.setUserid(809);
		driverAccept.setTruckno("粤BBB005");
		driverAccept.setDriver("李书良1");
		driverAccept.setPhone("13823698495");
		ArrayList<String> list = new ArrayList<String>();
		list.add(busiId);
		driverAccept.setBusinessids(list.toArray(new String[0]));
		System.out.println(JSONObject.fromObject(driverAccept).toString());
		JSONObject json = JSONObject.fromObject(driverAccept);
		json.put("methodname", FS_CancelOrder);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}
	
	/**
	 * 测试箱封号接口
	 * 
	 * @throws Exception
	 */
	private static void testFS_EditOrderContainerNo() throws Exception {
		DriverAccept driverAccept = new DriverAccept();
		driverAccept.setAccreditid(accreditId);
		driverAccept.setAppkey(appkey);
		driverAccept.setUserid(0);
		driverAccept.setTruckno("粤BBB005");
		driverAccept.setDriver("李书良1");
		driverAccept.setPhone("13823698495");
		driverAccept.setContainerno("APLU1111114");
		driverAccept.setSealno("ff001");
		driverAccept.setBusinessid(busiId);
		driverAccept.setConsealnoremark("箱封号的备注信息");
		ArrayList<String> list = new ArrayList<String>();
		list.add(busiId);
		driverAccept.setBusinessids(list.toArray(new String[0]));
		System.out.println(JSONObject.fromObject(driverAccept).toString());
		JSONObject json = JSONObject.fromObject(driverAccept);
		json.put("methodname", FS_EditOrderContainerNo);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}

	/**
	 * 测试同步费用接口
	 * 
	 * @throws Exception
	 */
	private static void testFS_SynchronizationFees() throws Exception {
		DriverFeeVO driverFeesVO = new DriverFeeVO();
		driverFeesVO.setAccreditid(accreditId);
		driverFeesVO.setAppkey(appkey);
		driverFeesVO.setUserid(0);
		driverFeesVO.setUsername("测试接口用户");
		driverFeesVO.setBusinessid(busiId);
		ArrayList<DriverFee> list = new ArrayList<DriverFee>();
		DriverFee fee = new DriverFee();
		fee.setFeeid(1398);
		fee.setAmount(22.5);
		list.add(fee);
		fee = new DriverFee();
		fee.setFeeid(1401);
		fee.setAmount(33);
		fee.setIspicture(1);
		list.add(fee);
		driverFeesVO.setFee(list.toArray(new DriverFee[0]));
		System.out.println(JSONObject.fromObject(driverFeesVO).toString());
		JSONObject json = JSONObject.fromObject(driverFeesVO);
		json.put("methodname", FS_SynchronizationFees);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www_tcb, arguments);
		System.out.println(rvalue);
	}
	
	public static String executeTcb(String www, Object[] arguments) throws Exception {
		RPCServiceClient service = null;
		String rvalue = StringManage.FS_EMPTY;
		try {
			// 使用RPC方式调用WebService
			service = new RPCServiceClient();
			Options options = service.getOptions();

			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(www);
			options.setTo(targetEPR);

			// 指定方法返回值的数据类型的Class对象
			Class<?>[] classes = new Class<?>[] { String.class };

			// 指定要调用的方法及WSDL文件的命名空间
			QName opAddEntry = new QName(S_namespace, "preExecute");
			rvalue = (String) service.invokeBlocking(opAddEntry, arguments, classes)[0];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (service != null) {
				service.cleanupTransport();// 关闭调用连接
			}
		}
		return rvalue;
	}

}
