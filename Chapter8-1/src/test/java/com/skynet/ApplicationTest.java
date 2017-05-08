package com.skynet;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author Skynet
 * @date 2017年05月08日 11:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());
        message.setTo("skynet_s@163.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo("skynet_s@163.com");
        helper.setSubject("主题：简单邮件");
        helper.setText("测试邮件内容");

//        使用FileSystemResource加载文件，放置在项目根路径下
//        FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
//        helper.addAttachment("附件-1.jpg", file);
//        helper.addAttachment("附件-2.jpg", file);

        //使用ClassPathResource加载文件，放置在类路径下
        helper.addAttachment("附件-1.jpg", new ClassPathResource("weixin.jpg"));

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo("skynet_s@163.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

        helper.addInline("weixin", new ClassPathResource("weixin.jpg"));

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo("skynet_s@163.com");
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashedMap();
        model.put("username", "didi");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "email.vm", "UTF-8", model);
        System.out.println(text);
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }


}
