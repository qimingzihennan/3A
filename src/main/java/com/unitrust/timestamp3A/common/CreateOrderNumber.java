package com.unitrust.timestamp3A.common;

import java.util.Date;

import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.DateUtil;

public class CreateOrderNumber {

	private volatile static CreateOrderNumber createOrderNumber;

	private CreateOrderNumber() {
	}

	public static CreateOrderNumber getCreateOrderNumber() {
		if (createOrderNumber == null) {
			synchronized (CreateOrderNumber.class) {
				if (createOrderNumber == null) {
					createOrderNumber = new CreateOrderNumber();
				}
			}
		}
		return createOrderNumber;
	}

	public synchronized String create(String Bkey) {
		String timestamp = new DateUtil("yyyyMMddhhmmssSSS").getDateStringByDate(new Date());
		Integer radomNum = (int) ((Math.random() * 9 + 1) * 100000);
		String mark = new Common().getBkeyDictionariesFromProperties(Bkey);
		StringBuffer numberBuffer = new StringBuffer(mark);
		numberBuffer.append(timestamp).append(radomNum.toString());
		return numberBuffer.toString();
	}
}
