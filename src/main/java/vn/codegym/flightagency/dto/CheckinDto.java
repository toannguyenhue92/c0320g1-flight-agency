package vn.codegym.flightagency.dto;

import vn.codegym.flightagency.model.Airport;

public class CheckinDto {
    private Long bookingCode;

    private String fullName;

    private Airport departure;

    private Airport arrival;

    public CheckinDto() {
    }

    public Long getBookingCode() {
        return bookingCode;
    }

    public String getFullName() {
        return fullName;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setBookingCode(Long bookingCode) {
        this.bookingCode = bookingCode;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }
}
