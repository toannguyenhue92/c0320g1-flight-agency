package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    // Creator: Duy
    @Autowired
    private JavaMailSender javaMailSender;

    // Creator: Duy
    @Override
    public void sendBookingCode(Long code, FlightSchedule flight, String... sendTo) {
        StringBuilder text = new StringBuilder();

        // set text
        text.append("Your flight reservation has been successfully confirmed. Please find your e-ticket attached.");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);
        String subject = "[C03Air] Your " +
                flight.getBranch().getName() +
                " E-ticket - Booking ID " +
                code;
        msg.setSubject(subject);
        msg.setText(text.toString());
        javaMailSender.send(msg);
    }
}
