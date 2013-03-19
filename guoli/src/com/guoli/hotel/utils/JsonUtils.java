package com.guoli.hotel.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	
	public static final HashMap<String, Object> convertToMap(String json) {
		return new Gson().fromJson(json.toString(), new TypeToken<HashMap<String,Object>>(){}.getType());
	}

}
