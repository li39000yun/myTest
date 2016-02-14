package com.test.json;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONtoLowerTools {

	public static JSONObject transObject(JSONObject o1) {
		JSONObject o2 = new JSONObject();
		@SuppressWarnings("rawtypes")
		Iterator it = o1.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object object = o1.get(key);
			if (object.getClass().toString().endsWith("JSONObject")) {
				o2.accumulate(key.toLowerCase(), JSONtoLowerTools.transObject((JSONObject) object));
			} else if (object.getClass().toString().endsWith("JSONArray")) {
				o2.accumulate(key.toLowerCase(), JSONtoLowerTools.transArray(o1.getJSONArray(key)));
			} else {
				o2.accumulate(key.toLowerCase(), object);
			}
		}
		return o2;
	}

	public static JSONArray transArray(JSONArray o1) {
		JSONArray o2 = new JSONArray();
		for (int i = 0; i < o1.toArray().length; i++) {
			Object jArray = o1.getJSONObject(i);
			if (jArray.getClass().toString().endsWith("JSONObject")) {
				o2.add(JSONtoLowerTools.transObject((JSONObject) jArray));
			} else if (jArray.getClass().toString().endsWith("JSONArray")) {
				o2.add(JSONtoLowerTools.transArray((JSONArray) jArray));
			}
		}
		return o2;
	}

}