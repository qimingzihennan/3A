package com.unitrust.timestamp3A.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;

public class MailSender {

	/**
	  * @Title: send
	  *
	  * @param title 邮件标题
	  * @param toMails 收件人列表
	  * @param content 邮件内容
	  * @param fileList 附件列表 无附件需传入null
	  * @throws MessagingException
	  * @throws IOException
	  * @return void
	  * @throws
	  */
	public static void send(String title, List<String> toMails, String content, List<File> fileList)
			throws MessagingException, IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(Mail.class.getClassLoader().getResourceAsStream("mail.properties"), "UTF-8"));

		Map<String, String> map = new HashMap<String, String>();
		prop.getProperty("mail.fromMail");
		Mail mail = new Mail(prop.getProperty("mail.fromMail"), prop.getProperty("mail.password"));
		map.put("mail.smtp.host", prop.getProperty("mail.host"));
		map.put("mail.smtp.ssl.enable", "true");
		map.put("mail.transport.protocol", prop.getProperty("mail.transport.protocol"));
		map.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
		map.put("mail.smtp.port", "465");
		mail.setPros(map);
		mail.initMessage();
		mail.setRecipients(toMails);
		mail.setSubject(prop.getProperty("mail.title") + title);
		mail.setDate(new Date());
		mail.setFrom(prop.getProperty("mail.fromMail"));
		if(fileList !=null){
			mail.setMultiparts(fileList);
		}
		mail.setContent(content, "text/html; charset=UTF-8");
		
		mail.sendMessage();
	}

}