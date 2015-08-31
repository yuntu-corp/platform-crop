package com.unitever.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UUID {

	/**
	 * 得到唯一的32位标识
	 */
	public static final String getUUID() {
		StringBuffer idBuffer = new StringBuffer();
		Random r = new Random();
		idBuffer.append(new SimpleDateFormat("dHHmmssSSS")
				.format(new Date()));
		for (int i = 0; i < 1; i++) {
			idBuffer.append(r.nextInt(100) % 10);
		}
		return "1"+idBuffer.toString();
	}
}
