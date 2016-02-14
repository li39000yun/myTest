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
public class TestYibutong {
	private static String S_namespace = "http://www.szyt.net";
	private static String www = "http://service.cttms.com:8180/service_center/services/YiBuTongService";

	//private static String www = "http://ys.cttms.com/services/YiBuTongService";
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
		container.setAppointDate("2015-12-28");
		container.setExternalId("20151228");
		container.setExternalSequence(1);
		container.setContainerType("20GP");
		container.setReturnConPile("cs");
		container.setTimePoint("08:12:11");
		list.add(container);
		container = new YiBuTongContainer();
		container.setAppointDate("2015-12-28");
		container.setExternalId("20151228");
		container.setExternalSequence(2);
		container.setContainerType("20GP");
		container.setReturnConPile("cs");
		container.setTimePoint("08:12:11");
		list.add(container);
		vo.setContainer(list.toArray(new YiBuTongContainer[0]));
//		String ss = "{
//		    "accreditid": "nbtms",
//		    "appkey": "4670af91302f9beadb49a802dbb0dcaa",
//		    "base": {
//		        "accreditId": "nbtms",
//		        "appendix": "",
//		        "bookingNo": "111111",
//		        "customerContact": "1111111",
//		        "customerTelephone": "1111111111",
//		        "customerUnit": "易步通科技有限公司",
//		        "cutOffTime": "2016-01-15 10:41:46",
//		        "followRemark": "",
//		        "invoiceAddress": "",
//		        "isInvoice": "0",
//		        "sectionDataTime": "2015-12-24 10:41:45",
//		        "ship": "111111",
//		        "startCustoms": "",
//		        "takeConAddress": "1111111",
//		        "takeConContact": "11111111",
//		        "takeConTelephone": "111111111",
//		        "takeConUnit": "11111111",
//		        "voyage": "1111111"
//		    },
//		    "container": [
//		        {
//		            "address": "11111111111111",
//		            "appointDate": "2015-12-22",
//		            "contact": "1111",
//		            "containerType": "20GP",
//		            "externalId": "TTO-00000000033",
//		            "externalSequence": 83,
//		            "factoryShortName": "11111111",
//		            "getConPile": "111111",
//		            "loadPlace": "浦江",
//		            "returnConPile": "B2SCT",
//		            "telephone": "111111111",
//		            "timePoint": "10:41:30",
//		            "weight": "11111111"
//		        },
//		        {
//		            "address": "11111111111",
//		            "appointDate": "2015-12-16",
//		            "contact": "11111",
//		            "containerType": "20GP",
//		            "externalId": "TTO-00000000033",
//		            "externalSequence": 84,
//		            "factoryShortName": "1111111",
//		            "getConPile": "11111111",
//		            "loadPlace": "鄞州东潘火",
//		            "returnConPile": "B2SCT",
//		            "telephone": "1111111111",
//		            "timePoint": "10:42:30",
//		            "weight": "11111111"
//		        }
//		    ]
//		}";

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
