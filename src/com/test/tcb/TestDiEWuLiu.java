/**
 * 
 */
package com.test.tcb;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.kingsoft.control.util.StringManage;

/**
 * 
 * @author xiehui
 * 
 * @version 2016-1-20
 * 
 * @since JDK 1.6
 * 
 */
public class TestDiEWuLiu {

	/**
	 * @param args
	 */
	// TODO Auto-generated method stub
	private static String S_namespace = "http://www.szyt.net";
	private static String www = "http://service.cttms.com:8180/service_center/services/DiEWuLiuService";
	private static String appkey = "4670af91302f9beadb49a802dbb0dcaa";

	public static void main(String[] args) throws Exception {
		testAdd();
	}

	/**
	 * 测试同步费用接口
	 * 
	 * @throws Exception
	 */
	private static void testAdd() throws Exception {
		JSONObject json = new JSONObject();
		json.put("accreditid", "diewuliu");
		json.put("accreditId", "nbtms");
		json.put("accessindex", 1);
		json.put("datasize", 200);
		json.put("appkey", appkey);
		json.put("begindate", "2015-01-01");
		json.put("enddate", "2015-12-31");
		Object[] arguments = new Object[] { json.toString() };
		String rvalue = executeTcb(www, arguments);
	
		JSONObject jsonObject = JSONObject.fromObject(rvalue);
		if (jsonObject.containsKey("data")){
			DiEWuLiu[] diEWuLius=(DiEWuLiu[]) JSONArray.toArray(JSONArray.fromObject(jsonObject.get("data")),DiEWuLiu.class);
			for (DiEWuLiu d:diEWuLius){
				System.out.println(d.getCount()+"---"+d.getBusiId()+"----"+d.getIncomeFee());
			}
		}
	}

	public static String executeTcb(String www, Object[] arguments)
			throws Exception {
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
			rvalue = (String) service.invokeBlocking(opAddEntry, arguments,
					classes)[0];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (service != null) {
				service.cleanupTransport();// 关闭调用连接
			}
		}
		JSONObject jsonObject = JSONObject.fromObject(rvalue);
		System.out.println(jsonObject.toString());
		return rvalue;
	}

}
