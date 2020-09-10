package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.dto.employeeInfoDto;

public interface PassengerService {


    //creator : Mậu
    Page<Passenger> getAllCustomer(Pageable page);

}
