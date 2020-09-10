package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.service.EmailService;

//CREATE BY ANH DUC
@Service
public class EmailServiceImpl implements EmailService {
    //CREATE BY ANH DUC
    @Autowired
    private JavaMailSender javaMailSender;

    //CREATE BY ANH DUC
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("CGB Airlines <CGBAirlines@gmail.com>");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
