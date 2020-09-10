package vn.codegym.flightagency.service;

public interface EmailService {
    //CREATE BY ANH DUC
    void sendSimpleMessage(String to, String subject, String text);
}
