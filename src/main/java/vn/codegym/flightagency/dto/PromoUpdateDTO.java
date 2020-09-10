package vn.codegym.flightagency.dto;

import lombok.Data;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.Branch;

import java.time.LocalDateTime;

@Data
public class PromoUpdateDTO {
    private Long id;
    private String promoName;
    private Double discount;
    private Branch airline;
    private Airport departurePlace;
    private Airport arrivalPlace;
    private LocalDateTime flightDepartureDateStart;
    private LocalDateTime flightDepartureDateEnd;
    private LocalDateTime promoDateStart;
    private LocalDateTime promoDateEnd;

}
