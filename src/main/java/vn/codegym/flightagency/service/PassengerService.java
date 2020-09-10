package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Passenger;

public interface PassengerService {

    // Creator: Duy
    Passenger saveAndUpdate(PassengerInfoDTO _passenger);
}
