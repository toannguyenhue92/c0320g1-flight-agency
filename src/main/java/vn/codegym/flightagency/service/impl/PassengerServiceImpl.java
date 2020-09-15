package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public PassengerDTO findPassengerDtoByUserId(Long id) {
        PassengerDTO passengerDTO = new PassengerDTO();
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if (passenger != null) {
            passengerDTO.setFullName(passenger.getFullName());
            passengerDTO.setBirthDate(passenger.getBirthDate());
            passengerDTO.setGender(passenger.getGender());
            passengerDTO.setEmail(passenger.getEmail());
            passengerDTO.setAddress(passenger.getAddress());
            passengerDTO.setPhoneNumber(passenger.getPhoneNumber());
            passengerDTO.setIdentifierCard(passenger.getIdentifierCard());
            return passengerDTO;
        }
        return null;
    }

    @Override
    public Passenger findPassengerById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }


    @Override
    public void updatePassenger(PassengerDTO passengerDTO, Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        assert passenger != null;
        passenger.setFullName(passengerDTO.getFullName());
        passenger.setBirthDate(passengerDTO.getBirthDate());
        passenger.setGender(passengerDTO.getGender());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setPhoneNumber(passengerDTO.getPhoneNumber());
        passenger.setIdentifierCard(passengerDTO.getIdentifierCard());

        List<Passenger> passengers = passengerRepository.findAll();
//        List<String> messages = new ArrayList<>();
//        for (Passenger testPassenger : passengers) {
//            if (!passenger.getIdentifierCard().equals(passengerDTO.getIdentifierCard().trim()) && testPassenger.getIdentifierCard().equals(passengerDTO.getIdentifierCard().trim())) {
//                messages.add("Chứng minh nhân dân/hộ chiếu này đã được đăng kí.");
//                break;
//            }
//        }
//        passenger.setIdentifierCard(passengerDTO.getIdentifierCard().trim());
//        passengerDTO.setBackendMessage(messages);
//        if (passengerDTO.getBackendMessage().size() == 0) {
//            passengerRepository.save(passenger);
//        }
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger create(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setFullName(passengerDTO.getFullName());
        passenger.setBirthDate(passengerDTO.getBirthDate());
        passenger.setGender(passengerDTO.getFullName());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setPhoneNumber(passengerDTO.getPhoneNumber());
        passenger.setIdentifierCard(passengerDTO.getIdentifierCard());
        passengerRepository.save(passenger);
        return passenger;
    }

}
