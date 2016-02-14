package com.test.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kingsoft.control.util.StringManage;
import com.test.tcb.vo.JsonMsg;

/**
 * JSON帮助类
 * 
 * @author liyunqiang
 * 
 * @version 2015-10-30
 */
public class JsonHelper {
	public static final int FS_JSON_TYPE_OBJECT = 201;// Json对象格式
	public static final int FS_JSON_TYPE_ARRAY = 202;// Json数组格式
	public static final int FS_JSON_TYPE_ERROR = 203;// 非Json格式
	public static final int FS_FAIL = -1; // 操作失败
	public static final int FS_SUCCESS = 0; // 0:成功
	public static final int FS_JSON_TYPE_WORNG = 1;// json格式错误
	public static final int FS_APPKEY_WORNG = 2;// 密钥错误
	public static final int FS_SERVICE_WORNG = 3;// 内部服务器错误
	public static final int FS_BUSINESSID_EMPTY = 4;// 业务编号不能为空
	public static final int FS_TRUCK_INFO_EMPTY = 5;// 没有派车（车辆信息不能为空）
	public static final int FS_BUSINESSID_NO_EXIST = 6;// 业务编号不存在对应的单
	public static final int FS_FEEID_NO_EXIST = 7;// feeid不存在对应的费用
	public static final int FS_TRUCK_NO_EXIST = 8;// 该车辆没有建档
	public static final int FS_DRIVER_NO_EXIST = 9;// 该司机没有建档
	public static final int FS_MANAGE_NO_EXIST = 10;// 没有对应的挂靠费信息
	public static final int FS_DATABASE_CONNTION_WORNG = 11;// 数据库连接错误
	public static final int FS_METHOD_NO_EXIST = 12;// 没有对应的接口方法名
	public static final int FS_BUSINESSID_WORNG = 13;// 业务编号格式错误
	public static final int FS_ACCREDITID_WORNG = 14;// 授权编号验证失败

	/**
	 * 根据传入的JSON字符串获取JSON字符串的类型
	 * 
	 * @param jsonString
	 *            JSON字符串
	 * @return Integer
	 */
	public static int getJSONType(String jsonString) {
		if (jsonString == null) {
			return FS_JSON_TYPE_ERROR;
		}
		jsonString = jsonString.trim();
		if (StringManage.isEmpty(jsonString)) {
			return FS_JSON_TYPE_ERROR;
		}
		final char[] strChar = jsonString.substring(0, 1).toCharArray();
		final char firstChar = strChar[0];
		if (firstChar == '{') {
			return FS_JSON_TYPE_OBJECT;
		} else if (firstChar == '[') {
			return FS_JSON_TYPE_ARRAY;
		} else {
			return FS_JSON_TYPE_ERROR;
		}
	}

	/**
	 * 把Json字符串转化成对象
	 * 
	 * @param json
	 *            Json字符串
	 * @param clazz
	 *            需转化的类对象
	 * @return Object
	 */
	public static Object jsonToObj(String json, Class<?> clazz) {
		if (getJSONType(json) == FS_JSON_TYPE_OBJECT) {
			return JSONObject.toBean(JSONObject.fromObject(json), clazz);
		} else if (getJSONType(json) == FS_JSON_TYPE_ARRAY) {
			return JSONArray.toArray(JSONArray.fromObject(json), clazz);
		}
		return new Object();
	}

	/**
	 * 根据传入的结果获取相应的JSON对象
	 * 
	 * @param rvalue
	 *            结果值
	 * @return JSONObject
	 */
	public static JsonMsg toJsonObject(int rvalue) {
		JsonMsg jsonObject = new JsonMsg();
		jsonObject.setRet(rvalue);
		switch (rvalue) {
		case FS_SUCCESS:
			jsonObject.setMsg("success");
			break;
		case FS_JSON_TYPE_WORNG:
			jsonObject.setMsg("json格式错误!");
			break;
		case FS_APPKEY_WORNG:
			jsonObject.setMsg("appkey密钥错误!");
			break;
		case FS_SERVICE_WORNG:
			jsonObject.setMsg("内部服务器错误!");
			break;
		case FS_BUSINESSID_EMPTY:
			jsonObject.setMsg("业务编号不能为空!");
			break;
		case FS_TRUCK_INFO_EMPTY:
			jsonObject.setMsg("车辆信息不能为空!");
			break;
		case FS_BUSINESSID_NO_EXIST:
			jsonObject.setMsg("业务编号不存在对应的单!");
			break;
		case FS_FEEID_NO_EXIST:
			jsonObject.setMsg("feeid不存在对应的费用!");
			break;
		case FS_FAIL:
			jsonObject.setMsg("操作失败!");
			break;
		case FS_TRUCK_NO_EXIST:
			jsonObject.setMsg("车辆不存在,请先建档!");
			break;
		case FS_DRIVER_NO_EXIST:
			jsonObject.setMsg("司机不存在,请先建档!");
			break;
		case FS_MANAGE_NO_EXIST:
			jsonObject.setMsg("没有对应的挂靠费信息!");
			break;
		case FS_JSON_TYPE_ERROR:
			jsonObject.setMsg("json格式错误!");
			break;
		case FS_DATABASE_CONNTION_WORNG:
			jsonObject.setMsg("数据库连接错误!");
			break;
		case FS_METHOD_NO_EXIST:
			jsonObject.setMsg("没有对应的接口方法名!");
			break;
		case FS_BUSINESSID_WORNG:
			jsonObject.setMsg("业务编号格式错误!");
			break;
		case FS_ACCREDITID_WORNG:
			jsonObject.setMsg("授权编号验证失败!");
			break;
		default:
			break;
		}
		return jsonObject;
	}

	/**
	 * 根据传入的结果获取相应的JSON字符串
	 * 
	 * @param rvalue
	 *            结果值
	 * @return jsonString
	 */
	public static String toJsonString(int rvalue) {
		return JSONObject.fromObject(toJsonObject(rvalue)).toString();
	}

	/**
	 * 将JAVA对象转化成JSON字符串
	 * 
	 * @param jsonMsg
	 *            需转化的JAVA对象
	 * @return jsonString
	 */
	public static String toString(JsonMsg jsonMsg) {
		return JSONObject.fromObject(jsonMsg).toString();
	}
}
