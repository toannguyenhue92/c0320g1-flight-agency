package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.service.FlightScheduleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class FlightScheduleController {
    //D-Bach
    @Autowired
    FlightScheduleService flightScheduleService;

    //D-Bach
    @GetMapping("/flight-schedule")
    public ResponseEntity<Page<FlightSchedule>> getAllFlightSchedule(@PageableDefault(value = 8) Pageable pageable) {
        Page<FlightSchedule> flightSchedulePage;
        flightSchedulePage = flightScheduleService.findAllFlightSchedule(pageable);
        if (flightSchedulePage == null) {
            return new ResponseEntity<Page<FlightSchedule>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<FlightSchedule>>(flightSchedulePage, HttpStatus.OK);
    }
}
