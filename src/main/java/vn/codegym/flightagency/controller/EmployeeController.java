package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.service.PassengerService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/employee/customer-checkin-list")
    public ResponseEntity<Page<Passenger>> getAllPassengerCheckin(@PageableDefault(value = 4)Pageable pageable){

    }

}
