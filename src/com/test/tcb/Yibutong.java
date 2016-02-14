package com.test.tcb;

import java.util.ArrayList;

import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.kingsoft.control.util.StringManage;
import com.test.tcb.vo.YiBuTongBase;
import com.test.tcb.vo.YiBuTongContainer;
import com.test.tcb.vo.YiBuTongVO;

/**
 * 
 * @author wmy
 * 
 * @version 2015-8-27
 * 
 * @since JDK 1.6
 * 
 */
public class Yibutong {
	private static String S_namespace = "http://www.szyt.net";
	private static String www = "http://ybt.nbport.com.cn/eport-dataservices/EportWebServicePort";
	private static String appkey = "4670af91302f9beadb49a802dbb0dcaa";

	public static void main(String[] args) throws Exception {
		testAdd();// 车队在线下单
		
//		DES des = new DES();
//		System.out.println(des.encrypt("kingsoft"));
//		System.out.println(des.decrypt("4670af91302f9beadb49a802dbb0dcaa"));
	}

	
	/**
	 * 测试同步费用接口
	 * 
	 * @throws Exception
	 */
	private static void testAdd() throws Exception {
		YiBuTongVO vo = new YiBuTongVO();
		vo.setAppkey(appkey);
		vo.setAccreditid("nbtms");
		YiBuTongBase base = new YiBuTongBase();
		base.setAccreditId("nbtms");
		base.setCustomerUnit("测试");
		vo.setBase(base);
		
		ArrayList<YiBuTongContainer> list = new ArrayList<YiBuTongContainer>();
		YiBuTongContainer container = new YiBuTongContainer();
		container.setAppointDate("2015-12-18");
		container.setExternalId("20151120");
		container.setExternalSequence(1);
		container.setContainerType("20GP");
		container.setReturnConPile("cs");
		list.add(container);
		container = new YiBuTongContainer();
		container.setAppointDate("2015-12-18");
		container.setExternalId("20151120");
		container.setExternalSequence(2);
		container.setContainerType("20GP");
		container.setReturnConPile("cs");
		list.add(container);
		vo.setContainer(list.toArray(new YiBuTongContainer[0]));
		System.out.println(JSONObject.fromObject(vo).toString());
		JSONObject json = JSONObject.fromObject(vo);
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www, arguments);
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
