package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<Passenger> addAllPassengers(List<PassengerInfoDTO> passengerInfoDtoList) {
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < passengerInfoDtoList.size(); i++) {
            Passenger passenger = saveAndUpdate(passengerInfoDtoList.get(i));
            passengerList.add(passenger);
        }
        return passengerList;
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

    @Override
    public Page<Passenger> getAllCustomer(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.by("full_name"));

        return passengerRepository.findAllCustomer(pageable);


    }
}
