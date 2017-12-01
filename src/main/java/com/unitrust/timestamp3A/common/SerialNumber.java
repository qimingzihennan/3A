package com.unitrust.timestamp3A.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取订单流水号时间+五位数字
 * @author tsa06
 *
 */
public class SerialNumber {
	
	public static String getSerialNumber(String maxOrderno){	
		String Orderno = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH"); // 时间字符串产生方式
        String uid_pfix =format.format(new Date()); // 组合流水号前一部分，时间字符串，如：2016012601
        if (maxOrderno != null && maxOrderno.contains(uid_pfix)) {
            String uid_end = maxOrderno.substring(10, 15); // 截取字符串最后五位，结果:0001
            int endNum = Integer.parseInt(uid_end); // 把String类型的0001转化为int类型的1
            int tmpNum = 100000 + endNum + 1; // 结果100002
            Orderno = uid_pfix + subStr("" + tmpNum, 1);// 把100002首位的1去掉，再拼成2016012600002字符串
        } else {
            Orderno = uid_pfix + "00001";
        }
	return Orderno;
	}
	
    public static String subStr(String str, int start) {
        if (str == null || str.equals("") || str.length() == 0)
            return "";
        if (start < str.length()) {
            return str.substring(start);
        } else {
            return "";
        }

    }
    
    public static void main(String args[]) {
    	System.out.println(getSerialNumber("201704251000001"));
    }
}

