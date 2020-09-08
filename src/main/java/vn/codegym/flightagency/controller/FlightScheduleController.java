package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.exception.ViolatedException;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.service.AirportService;
import vn.codegym.flightagency.service.FlightScheduleService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class FlightScheduleController {

    @Autowired
    private FlightScheduleService flightScheduleService;

    @Autowired
    private AirportService airportService;

    // Creator: Duy
    // Find flight schedule
    @PostMapping("/flight-schedule")
    public ResponseEntity<List<FlightSchedule>> search(@RequestBody FlightSearchDTO flights)
            throws ViolatedException {
        List<FlightSchedule> flightSchedules = flightScheduleService.searchFlights(flights);
        return ResponseEntity.ok(flightSchedules);
    }

    // Creator: Duy
    // Get all airport
    @GetMapping("/airport")
    public ResponseEntity<List<Airport>> getAirports() {
        List<Airport> airports = airportService.getAirports();
        return ResponseEntity.ok(airports);
    }


}
