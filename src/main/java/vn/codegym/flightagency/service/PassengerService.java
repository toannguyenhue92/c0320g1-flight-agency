package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Passenger;

import java.time.LocalDate;


public interface PassengerService {
    // creator: Mậu

    Page<Passenger> getAllCustomer(Pageable page);

    Page<Passenger> findByEmail(String email, Pageable pageable);

    Page<Passenger> findAllByFullName(String name, Pageable pageable);

    Page<Passenger> findAllByBirthday(LocalDate date, Pageable pageable);

    Page<Passenger> findByPhoneNumber(String phoneNumber, Pageable pageable);

    Page<Passenger> findAllByGender(String gender, Pageable pageable);
    

    
}


