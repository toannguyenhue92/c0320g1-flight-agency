package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Passenger;

import java.util.List;

public interface PassengerService {

    // Creator: Duy
    Passenger saveAndUpdate(PassengerInfoDTO _passenger);

    // Creator: Duy
    List<Passenger> addAllPassengers(List<PassengerInfoDTO> passengerInfoDtoList);
    //creator : Mậu
    Page<Passenger> getAllCustomer(Pageable page);
}
