package com.unitrust.timestamp3A.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unitrust.timestamp3A.service.consume.impl.ConsumeServiceImpl;

public class DateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private SimpleDateFormat df;

	public DateUtil(String DateFormat) {
		this.df = new SimpleDateFormat(DateFormat); // 制定日期格式
	}

	public static void main(String[] args) throws ParseException {
		Date date = new DateUtil("yyyy-MM-dd HH:mm:ss").getDateByMonth(new Date(), 1);
		System.out.println(date);
	}

	/**
	 * 通过Date生成String 对象
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public String getDateStringByDate(Date date) {
		if (date == null) {
			logger.warn("时间数据为null，返回\"\" 空字符串。");
			return "";
		}
		String result = df.format(date);
		return result;
	}

	public Date getDateByString(String dateString) {
		Date date = null;
		try {
			if (dateString == null || "".equals(dateString)  ) {
				logger.warn("dateString 时间字符串为空，无法转化。");
			}else{
				date = df.parse(dateString);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public Date getDateByMonth(Date date, int month) throws ParseException {
		// 2016-02-02,-3
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month); // 将当前日期加一个月
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		String validityDate = df.format(c.getTime()); // 返回String型的时
		return df.parse(validityDate);
	}

	public Date getDateByString(String time, Integer type) throws ParseException {
		Date date = df.parse(time);
		if (type == 1) {
			return date;
		}
		if (type == 2) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		}
		return new Date();
	}

	public Date getDate(int type, Integer days) {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		if (type == 1) {
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, 00);
			c.set(Calendar.MINUTE, 00);
			c.set(Calendar.SECOND, 00);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		} else {
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, days);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		}
	}

}
