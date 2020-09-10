package vn.codegym.flightagency.service;

public interface EmailService {
    // Creator: Duy
    void sendBookingCode(Long code, String branch, String... sendTo);
}
