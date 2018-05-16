package com.unitrust.timestamp3A.common;

import java.util.Date;

import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateOrderNumber {

	@Autowired
	private Common common;

	public synchronized String create(String Bkey) throws Exception {
		String timestamp = new DateUtil("yyyyMMddhhmmssSSS").getDateStringByDate(new Date());
		Integer radomNum = (int) ((Math.random() * 9 + 1) * 100000);
		String mark = common.getBkeyDictionariesFromProperties(Bkey);
		StringBuffer numberBuffer = new StringBuffer(mark);
		numberBuffer.append(timestamp).append(radomNum.toString());
		return numberBuffer.toString();
	}
}
