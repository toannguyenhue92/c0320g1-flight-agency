package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.model.Passenger;

public interface PassengerService {
    void savePassenger(Passenger passenger);

    PassengerDTO findPassengerDtoByUserId(Long id);

    Passenger findPassengerById(Long id);

    void updatePassenger(PassengerDTO passengerDTO, Long id);

    Passenger create(PassengerDTO passengerDTO);
}
