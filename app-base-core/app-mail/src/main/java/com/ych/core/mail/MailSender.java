package com.ych.core.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class MailSender {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMail(ToEmail toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail.getTos());
        message.setSubject(toEmail.getSubject());
        message.setText(toEmail.getContent());
        mailSender.send(message);
    }

    public void sendHtmlMail(ToEmail toEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toEmail.getTos());
        helper.setSubject(toEmail.getSubject());
        helper.setText(toEmail.getContent(), true);
        mailSender.send(message);
    }

    public void sendInlineResourceMail(ToEmail toEmail) {

    }

    public void sendAttachmentsMail(ToEmail toEmail, List<String> paths) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toEmail.getTos());
        helper.setSubject(toEmail.getSubject());
        helper.setText(toEmail.getContent(), true);
        for (String path : paths) {
            if (StringUtils.hasText(path)) {
                FileSystemResource resource = new FileSystemResource(path);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                helper.addAttachment(MimeUtility.encodeWord(fileName, "UTF-8", "B"), resource);
            }
        }
        mailSender.send(message);
    }

}
