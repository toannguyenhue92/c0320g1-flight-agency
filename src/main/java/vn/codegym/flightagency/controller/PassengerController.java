package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.service.PassengerService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    //creator: Mậu
    @GetMapping("/customer/management")
    public ResponseEntity<Page<Passenger>> getAllCustomerList(@RequestParam(name = "name", defaultValue = "") String fullName,
                                                              @RequestParam(name = "phoneNumber", defaultValue = "") String gender,
                                                              @RequestParam(name = "address", defaultValue = "") String email,
                                                              @RequestParam(name = "birthDate", defaultValue = "") String phoneNumber,
                                                              @PageableDefault Pageable currentPage) {
        Page<Passenger> customers = passengerService.getAllCustomer(currentPage);
        return ResponseEntity.ok(customers);
    }


}
