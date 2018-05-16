package com.unitrust.timestamp3A.mail;

import org.springframework.beans.factory.annotation.Value;

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

    @Value("${3A.mail.fromMail}")
    private String mail_fromMail;
    @Value("${3A.mail.password}")
    private String mail_password;
    @Value("${3A.mail.host}")
    private String mail_smtp_host;
    @Value("${3A.mail.transport.protocol}")
    private String mail_transport_protocol;
    @Value("${3A.mail.smtp.auth}")
    private String mail_smtp_auth;
    @Value("${3A.mail.title}")
    private String mail_title;

    /**
     * @param title    邮件标题
     * @param toMails  收件人列表
     * @param content  邮件内容
     * @param fileList 附件列表 无附件需传入null
     * @return void
     * @throws MessagingException
     * @throws IOException
     * @throws
     * @Title: send
     */
    public void send(String title, List<String> toMails, String content, List<File> fileList)
            throws MessagingException, IOException {

        Map<String, String> map = new HashMap<String, String>();
        Mail mail = new Mail(mail_fromMail, mail_password);
        map.put("mail.smtp.host", mail_smtp_host);
        map.put("mail.smtp.ssl.enable", "true");
        map.put("mail.transport.protocol", mail_transport_protocol);
        map.put("mail.smtp.auth", mail_smtp_auth);
        map.put("mail.smtp.port", "465");
        mail.setPros(map);
        mail.initMessage();
        mail.setRecipients(toMails);
        mail.setSubject(mail_title + title);
        mail.setDate(new Date());
        mail.setFrom(mail_fromMail);
        if (fileList != null) {
            mail.setMultiparts(fileList);
        }
        mail.setContent(content, "text/html; charset=UTF-8");

        mail.sendMessage();
    }

}