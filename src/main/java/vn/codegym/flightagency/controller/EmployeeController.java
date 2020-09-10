package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.dto.PassengerCheckinDto;
import vn.codegym.flightagency.service.PassengerService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private PassengerService passengerService;

    // Thành Long
    @GetMapping("/employee/customer-checkin-list")
    public ResponseEntity<Page<PassengerCheckinDto>> getAllPassengerCheckin(@RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                                            @RequestParam(name = "address", defaultValue = "") String address,
                                                                            @RequestParam(name = "page") int page) {
        Specification<Passenger> specs = passengerService.getFilter(fullName, address);
        Page<PassengerCheckinDto> passengers;
        if (specs != null) {
            passengers = passengerService.findPassengerByCriteria(specs, page);
        } else {
            passengers = passengerService.findAllPassengerCheckin(page);
        }

        if (passengers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(passengers);
    }

}
