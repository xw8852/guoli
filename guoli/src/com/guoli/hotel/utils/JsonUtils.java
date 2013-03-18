package com.guoli.hotel.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtils {
	
	@SuppressWarnings("rawtypes")
	public static final HashMap<String, String> convertJsonToHashMap(String json) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				map.put(key, jsonObject.get(key).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return map;
	}
}
