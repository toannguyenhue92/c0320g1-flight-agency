package vn.codegym.flightagency.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingDTO {

    private Long depFlightId;
    private Long retFlightId;
    private Long accountId;
    private Double depTotalPrice;
    private Double retTotalPrice;
    private List<PassengerInfoDTO> depPassengers;
    private List<PassengerInfoDTO> retPassengers;

}
