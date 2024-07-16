package com.example.fibonacci;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.office365.com");
        mailSender.setPort(587);
        mailSender.setProtocol("smtp");

        mailSender.setUsername("connievilla99@hotmail.com");
        mailSender.setPassword("@@Conie99");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "20000");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
