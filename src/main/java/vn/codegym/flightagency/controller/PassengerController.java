package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.exception.ResourceNotFoundException;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.PassengerService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PassengerController {
    @Autowired
    PassengerService passengerService;
    @Autowired
    PassengerRepository passengerRepository;

    @GetMapping("/passenger")
    public List<Passenger> getAllUsers() {
        return passengerRepository.findAll();
    }

    @PostMapping("/passenger/add")
    public ResponseEntity<Passenger> createPassenger(@Valid @RequestBody PassengerDTO passengerDTO) throws ResourceNotFoundException {
        Passenger passenger = passengerService.create(passengerDTO);
        if (passenger != null) {
            return ResponseEntity.ok().body(passenger);
        } else {
            throw new ResourceNotFoundException("Failed to create CartDetail");
        }
    }

//Creator: Hậu
@GetMapping("/passenger/{id}")
public ResponseEntity<PassengerDTO> findUserById(@PathVariable Long id) {
    PassengerDTO passengerDTO = passengerService.findPassengerDtoByUserId(id);
    if (passengerDTO == null) {
        return new ResponseEntity<PassengerDTO>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(passengerDTO);
}

    //Creator:  Hậu
    @PutMapping("passenger/update/{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable Long id, @RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = passengerService.findPassengerById(id);
        if (passenger == null) {
            return new ResponseEntity<PassengerDTO>(HttpStatus.NOT_FOUND);
        }
        passengerService.updatePassenger(passengerDTO, id);
        return new ResponseEntity<PassengerDTO>(passengerDTO, HttpStatus.OK);
    }
}

