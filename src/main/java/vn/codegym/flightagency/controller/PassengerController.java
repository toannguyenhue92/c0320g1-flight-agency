package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.service.PassengerService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    // Thành Long
    @PutMapping("checkin/checkin-list")
    public Map<String, Boolean> checkinPassenger(@RequestBody Map<String, Long[]> requestBody) {
        Long[] ids = requestBody.get("ids").clone();
        Map<String, Boolean> response = new HashMap<>();

        for (Long id : ids) {
            Passenger passenger = passengerService.findById(id);
            passengerService.checkinPassenger(passenger);
            response.put("checkin " + id, Boolean.TRUE);
        }
        return response;
    }

}
