package vn.codegym.flightagency.dto;

import lombok.Data;
import vn.codegym.flightagency.model.Airport;

//Creator: BHung search flight schedule trong employee
@Data
public class FlightSeachDTO {
    private Airport departurePlace;
    private Airport arrivalPlace;
    private String departureDate;
    private String arrivalDate;
    private int adult;
    private int child;
    private int baby;
}
