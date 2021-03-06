package com.song.messages.service;

import com.alibaba.fastjson.JSONObject;
import com.song.messages.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 发送邮件
 * created on 2019/3/8 16:29
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter {

    @Autowired
    private JavaMailSender mailSender; // 自动注入的Bean

    @Override
    public void distribute(JSONObject jsonObject) {

        String mail = jsonObject.getString("mail");
        String userName = jsonObject.getString("userName");
        log.info("--- 消费者收到消息... mail:{},userName:{}", mail, userName);
        // 开始发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(mail); // 自己给自己发送邮件
        message.setSubject("微信商城会员注册成功");
        message.setText("恭喜您" + userName + ",成为微信商城的新用户!谢谢您的光临!");
        log.info("--- 发送短信邮箱 mail:{}", mail);
        mailSender.send(message);
    }

}
