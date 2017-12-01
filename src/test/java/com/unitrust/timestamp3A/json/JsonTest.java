package com.unitrust.timestamp3A.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class JsonTest {

	private String jsonString;

	@Before
	public void makeJson() {
		JSONObject jo = new JSONObject();

		Object[] ob = new Object[3];
		for (int i = 0; i < 3; i++) {
			Map m = new HashMap();
			m.put("key1", 1);
			m.put("key2", 1);
			m.put("key3", 1);
			ob[i] = m;
		}

		jo.put("keys", ob);
		jsonString = jo.toString();
	}

	@Test
	public void output() {
		System.out.println(jsonString);
	}

}
