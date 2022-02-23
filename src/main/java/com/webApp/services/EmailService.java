package com.webApp.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
    private final JavaMailSender mailSender;

    private final SimpleMailMessage preConfiguredMessage;

    public EmailService(JavaMailSender mailSender, SimpleMailMessage preConfiguredMessage) {
        this.mailSender = mailSender;
        this.preConfiguredMessage = preConfiguredMessage;
    }

    public void sendMail(String to, String clientLastName, String clientFirstName, String secureCode) {
        String subject = "Reset password instructions";
        String body = "Hello" + " " + clientLastName + " " + clientFirstName + "\n" +
                "You link" + " " +"http://localhost:8080/restore/" + secureCode;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}