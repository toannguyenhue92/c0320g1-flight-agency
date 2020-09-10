package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.FlightSchedule;

public interface EmailService {
    // Creator: Duy
    void sendBookingCode(Long code, FlightSchedule flight, String... sendTo);
}
