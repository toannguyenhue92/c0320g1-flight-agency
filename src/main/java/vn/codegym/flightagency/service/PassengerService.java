package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Passenger;

public interface PassengerService {
    Page<Passenger> listPassengerCheckin(Pageable pageable);
}