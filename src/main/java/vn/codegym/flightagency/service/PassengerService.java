package vn.codegym.flightagency.service;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.dto.PassengerCheckinDto;
import vn.codegym.flightagency.dto.PassengerInfoDTO;

import java.util.List;

public interface PassengerService {
    // Thành Long
    Page<PassengerCheckinDto> findPassengerByCriteria(Specification<Passenger> spec, int page);

    //Thành Long
    Specification<Passenger> getFilter(String fullName, String address);

    //Thành Long
    Page<PassengerCheckinDto> findAllPassengerCheckin(int page);

    // Creator: Duy
    Passenger saveAndUpdate(PassengerInfoDTO _passenger);

    // Creator: Duy
    List<Passenger> addAllPassengers(List<PassengerInfoDTO> passengerInfoDtoList);


    void checkinPassenger(Passenger passenger);

    Passenger findById(Long id);

}
