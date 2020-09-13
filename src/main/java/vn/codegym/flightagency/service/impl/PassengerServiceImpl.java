package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Page<Passenger> getAllCustomer(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber()  , 5, Sort.by("full_name"));

        return passengerRepository.findAllCustomer(pageable);

    }


}
