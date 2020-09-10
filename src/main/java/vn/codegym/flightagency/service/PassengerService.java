package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Passenger;

import java.util.List;

public interface PassengerService {

    // Creator: Duy
    Passenger saveAndUpdate(PassengerInfoDTO _passenger);

    // Creator: Duy
    List<Passenger> addAllPassengers(List<PassengerInfoDTO> passengerInfoDtoList);
}
