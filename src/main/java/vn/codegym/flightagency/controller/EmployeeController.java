package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.service.AirportService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    AirportService airportService;

    //Hung lay danh sach san bay
    @GetMapping("/employee/airport")
    public ResponseEntity<List<Airport>> findAllAirport(){
        List<Airport> airports = airportService.findAllAirport();
        if(airports==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(airports);
    }
}
