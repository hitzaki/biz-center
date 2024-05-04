package com.git.hitzaki.message.service.impl;

import com.git.hitzaki.message.model.MailMessageParam;
import com.git.hitzaki.message.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author hitzaki
 */
@Service
public class SimpleMailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(MailMessageParam message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(message.getTitle());
        simpleMailMessage.setText(message.getText());
        simpleMailMessage.setFrom("1152849733@qq.com");
        simpleMailMessage.setTo(message.getTo());
        mailSender.send(simpleMailMessage);
    }
}
