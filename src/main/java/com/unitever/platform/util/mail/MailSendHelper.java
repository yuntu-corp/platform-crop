package com.unitever.platform.util.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.unitever.platform.core.spring.SpringContextHolder;

public class MailSendHelper {

	private static final Log log = LogFactory.getLog(MailSendHelper.class);

	public static void sendText(String toMail, String subject, String content) {
		if (StringUtils.isBlank(toMail) || subject == null || content == null) {
			throw new RuntimeException("发送邮件参数异常：toMail：" + toMail + " subject:" + subject + " content:" + content);
		}
		JavaMailSenderImpl sender = SpringContextHolder.getBeanOneOfType(JavaMailSenderImpl.class);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(sender.getUsername());
		mail.setTo(toMail);
		mail.setSubject(subject);
		mail.setText(content);
		sender.send(mail);
	}

	public static void sendHtml(String toMail, String subject, String content) {
		if (StringUtils.isBlank(toMail) || subject == null || content == null) {
			throw new RuntimeException("发送邮件参数异常：toMail：" + toMail + " subject:" + subject + " content:" + content);
		}
		JavaMailSenderImpl sender = SpringContextHolder.getBeanOneOfType(JavaMailSenderImpl.class);
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true, "GB2312");
			helper.setFrom(sender.getUsername());
			helper.setTo(toMail);
			helper.setSubject(subject);
			helper.setText(content, true);
			sender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("发送邮件失败", e);
			throw new MailSendException();
		}
	}

}
