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

import java.time.LocalDate;


@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;


    @Override
    public Page<Passenger> getAllCustomer(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("full_name"));

        return passengerRepository.findAllCustomer(pageable);

    }

    @Override
    public Page<Passenger> findByEmail(String email, Pageable pageable) {
        return passengerRepository.findAllByEmailContaining(email, pageable);
    }

    @Override
    public Page<Passenger> findAllByFullName(String name, Pageable pageable) {
        return passengerRepository.findAllByFullNameContaining(name, pageable);
    }

    @Override
    public Page<Passenger> findAllByBirthday(LocalDate date, Pageable pageable) {
        return passengerRepository.findAllByBirthDate(date, pageable);
    }

    @Override
    public Page<Passenger> findByPhoneNumber(String phoneNumber, Pageable pageable) {
        return passengerRepository.findAllByPhoneNumberContaining(phoneNumber, pageable);
    }


    @Override
    public Page<Passenger> findAllByGender(String gender, Pageable pageable) {
        return passengerRepository.findAllByGender(gender, pageable);
    }



}
