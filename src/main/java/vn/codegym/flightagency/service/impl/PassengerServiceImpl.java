package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    // Creator: Duy
    @Override
    public Passenger saveAndUpdate(PassengerInfoDTO _passenger) {
       Passenger passenger = findPassengerByIdCard(_passenger.getIdentifierCard());
       if (passenger != null) {
           passenger.setEmail(_passenger.getEmail());
           passenger.setPhoneNumber(_passenger.getPhoneNumber());
       } else {
           passenger = transferToPassenger(_passenger);
       }
       return passengerRepository.save(passenger);
    }

    // Creator: Duy
    private Passenger findPassengerByIdCard(String idCard) {
        return passengerRepository.findPassengerByIdentifierCard(idCard);
    }

    // Creator: Duy
    // transfer passenger
    private Passenger transferToPassenger(PassengerInfoDTO passengerInfoDto) {
        Passenger passenger = new Passenger();
        passenger.setFullName(passengerInfoDto.getFullName());
        passenger.setGender(passengerInfoDto.getGender());
        passenger.setEmail(passengerInfoDto.getEmail());
        passenger.setPhoneNumber(passengerInfoDto.getPhoneNumber());
        passenger.setIdentifierCard(passengerInfoDto.getIdentifierCard());
        return passenger;
    }
}
