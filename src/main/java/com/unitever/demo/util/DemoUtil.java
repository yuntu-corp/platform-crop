package com.unitever.demo.util;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class DemoUtil {

	public static void main(String[] args) {
		String str = "{1:'/年/',2:'/年/月/',3:'/年/月/日/' }";
		// JSONObject obj = JSON.parseObject(str);
		// for (String key : obj.keySet()) {
		// System.out.println(key);
		// }
		Object obj = JSON.parse(str);
		BasicDBObject obj2 = (BasicDBObject) obj;
		for (String key : obj2.keySet()) {
			System.out.println(key);
		}
	}
}
