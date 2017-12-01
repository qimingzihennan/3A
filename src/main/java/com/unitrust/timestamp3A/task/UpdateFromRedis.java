package com.unitrust.timestamp3A.task;



import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.unitrust.timestamp3A.mail.Mail;
import com.unitrust.timestamp3A.mail.MailSender;
import com.unitrust.timestamp3A.model.excuteLog.ExcuteLog;
import com.unitrust.timestamp3A.model.task.TaskJob;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
import com.unitrust.timestamp3A.service.excuteLog.ExcuteLogService;
import com.unitrust.timestamp3A.service.task.TaskJobService;
/**
 * 更新用户消费清单数据定时任务
 * @author tsa02
 *
 */
@Component  
@Aspect
public class UpdateFromRedis {
	private static final Logger log = LoggerFactory.getLogger(UpdateFromRedis.class);
	@Resource
	private ConsumeService consumeService;
	@Resource
	private TaskJobService taskJobService;
	@Resource
	private ExcuteLogService elog;
	//private Logger logger = Logger.getLogger(UpdateFromRedis.class);
	/**
	 * 更新用户消费清单数据
	 */
    @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行  
    public void updateTask(){  
    	ExcuteLog el = new ExcuteLog();
    	Double t = 0.0;
        long begin = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        Double s = Double.parseDouble(new DecimalFormat("#.##").format(begin));
		Double b = Double.parseDouble(new DecimalFormat("#.##").format(end));
		TaskJob job = new TaskJob();
		t = (b - s) / 1000;
        //执行你需要操作的定时任务
        try {
        	consumeService.updateCusConsumeInventoryFromRedis();
        	job.setJobId(1);
        	job.setJobStatus("0");
        	taskJobService.modifyStatus(job);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("====通知异常====");
			log.error("异常信息:{}", e.getMessage());
			el.setModule("更新用户消费清单数据定时任务");
			el.setMethods("updateCusConsumeInventoryFromRedis()");
			el.setActionTime(t.toString());
			el.setDescription(e.getMessage());
			el.setTaskId(1);
			elog.add(el);
			job.setJobId(1);
			job.setJobStatus("1");
			taskJobService.modifyStatus(job);
			try {
				String str = "尊敬的客户您好: <br/>         错误详细信息 <br/><br/>"+e.getMessage();
				sendMail(null, null, str);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
     
        
    }
    /**
     * 查询用户消费清单操作日志信息数据
     */
    @Scheduled(cron="0/10 * * * * ? ") //间隔5秒执行  
    public void saveTask(){  
    	ExcuteLog el = new ExcuteLog();
    	Double t = 0.0;
        long begin = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        Double s = Double.parseDouble(new DecimalFormat("#.##").format(begin));
		Double b = Double.parseDouble(new DecimalFormat("#.##").format(end));
		t = (b - s) / 1000;
		TaskJob job = new TaskJob();
        //执行你需要操作的定时任务
        try {
        	consumeService.saveCusConsumeInventoryLogFromRedis();
        	job.setJobId(2);
        	job.setJobStatus("0");
        	taskJobService.modifyStatus(job);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("====通知异常====");
			log.error("异常信息:{}", e.getMessage());
			el.setModule("生成用户消费清单操作日志信息数据");
			el.setMethods("saveCusConsumeInventoryLogFromRedis()");
			el.setActionTime(t.toString());
			el.setDescription(e.getMessage());
			el.setTaskId(2);
			elog.add(el);
			job.setJobId(2);
			job.setJobStatus("1");
			taskJobService.modifyStatus(job);
			try {
				String str = "尊敬的客户您好: <br/>         错误详细信息 <br/><br/>"+e.getMessage();
				sendMail(null, null,str);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    	
    	
    }  
    
    
    /**
	 * 发送邮件
	 */
	public void sendMail(HttpServletRequest request, HttpServletResponse response,String str) throws Exception{
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
		String email = prop.getProperty("mail.recipient");
		List<String> toEmail = new ArrayList<String>();
		toEmail.add(email);
		MailSender.send("定时器错误信息", toEmail, str, null);
	}
	
	/**
	 * BASE64加密
	 * @param key
	 * @throws Exception
	 */
	public String encryptBASE64(String key) {
		return toHexString(new BASE64Encoder().encodeBuffer(key.getBytes()));
	}
	
	/**
     * BASE64解密
     * @param key
	 * @throws IOException 
     * @throws Exception
     */
    public String decryptBASE64(String key) throws IOException{
        return new String(new BASE64Decoder().decodeBuffer(toStringHex(key)));
    }
    
	public String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}
	
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
}
