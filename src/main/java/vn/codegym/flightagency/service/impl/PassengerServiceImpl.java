package vn.codegym.flightagency.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.dto.PassengerCheckinDto;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;
import vn.codegym.flightagency.service.search.PassengerSpecification;
import vn.codegym.flightagency.service.search.SearchCriteria;
import java.util.ArrayList;
import java.util.Iterator;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import java.util.List;

import java.time.LocalDate;


@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    // Thành Long
    private PassengerCheckinDto transferToDTO(Passenger temp) {
        PassengerCheckinDto passenger = new PassengerCheckinDto();
        passenger.setFullName(temp.getFullName());
        passenger.setBirthDate(temp.getBirthDate());
        passenger.setGender(temp.getGender());
        passenger.setEmail(temp.getEmail());
        passenger.setPhoneNumber(temp.getPhoneNumber());
        passenger.setAddress(temp.getAddress());
        return passenger;
    }

    // Thành Long
    private Page<PassengerCheckinDto> transferToNewPage(Page<Passenger> passengers) {
        Passenger temp;
        List<PassengerCheckinDto> passengerDto = new ArrayList<>();
        Iterator iterator = passengers.iterator();
        while (iterator.hasNext()) {
            temp = (Passenger) iterator.next();
            passengerDto.add(transferToDTO(temp));
        }
        Page<PassengerCheckinDto> _passenger = new PageImpl<>(passengerDto, passengers.getPageable(), passengers.getTotalElements());
        return _passenger;
    }

    // Thành Long
    @Override
    public Page<PassengerCheckinDto> findPassengerByCriteria(Specification<Passenger> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Passenger> passengers = passengerRepository.findAll(spec, pageable);
        return transferToNewPage(passengers);
    }

    // Thành Long
    @Override
    public Specification<Passenger> getFilter(String fullName, String address) {
        List<PassengerSpecification> specs = new ArrayList<>();
        Specification<Passenger> spec;
        // search theo name
        if(!"".equals(fullName) && !"undefined".equals(fullName)) {
            specs.add(new PassengerSpecification(new SearchCriteria("fullName", "like", fullName)));
        }
        // search theo address
        if(!"".equals(address) && !"undefined".equals(address) ) {
            specs.add(new PassengerSpecification(new SearchCriteria("address", "like", address)));
        }
        if (specs.size() != 0) {
            spec = Specification.where(specs.get(0));
            for (int i = 1; i < specs.size(); i++) {
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

    // Thành Long
    @Override
    public Page<PassengerCheckinDto> findAllPassengerCheckin(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Passenger> passengers = passengerRepository.findAllByCheckinIsTrue(pageable);
        return transferToNewPage(passengers);
    }

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

    // Thành Long
    @Override
    public void checkinPassenger(Passenger passenger) {
        passenger.setCheckin(true);
        passengerRepository.save(passenger);
    }

    // Thành Long
    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
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
