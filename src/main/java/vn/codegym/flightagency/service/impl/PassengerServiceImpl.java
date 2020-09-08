package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Page<Passenger> listPassengerCheckin(Pageable pageable) {
        return passengerRepository.findAllByCheckinIsTrue(pageable);
    }
}
