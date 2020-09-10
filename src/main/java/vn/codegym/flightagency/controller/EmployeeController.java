package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.FlightSeachDTO;
import vn.codegym.flightagency.dto.TransactionPassengerDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.AirportService;
import vn.codegym.flightagency.service.EmployeeService;
import vn.codegym.flightagency.service.FlightScheduleService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    AirportService airportService;
    @Autowired
    FlightScheduleService flightScheduleService;
    @Autowired
    AccountService accountService;
    @Autowired
    EmployeeService employeeService;

    //BHung lay danh sach san bay
    @GetMapping("/employee/airport")
    public ResponseEntity<List<Airport>> findAllAirport(){
        List<Airport> airports = airportService.findAllAirport();
        if(airports==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(airports);
    }

    //BHung lay danh sach chuyến bay
    @PostMapping("/employee/flightSchedule")
    public ResponseEntity<List<FlightSchedule>> findAllFlightSchedules(@RequestBody FlightSeachDTO flightSeachDTO){
        List<FlightSchedule> flightSchedules = flightScheduleService.findAllFlightScheduleByEmployee(flightSeachDTO.getDeparturePlace().getId(),
                flightSeachDTO.getArrivalPlace().getId(), flightSeachDTO.getDepartureDate(),
                flightSeachDTO.getAdult()+ flightSeachDTO.getChild(),"active");
        if (flightSchedules==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(flightSchedules);
    }

    //BHung tim kiem chuyen bay theo id
    @GetMapping("/employee/flightSchedule/{id}")
    public ResponseEntity<FlightSchedule> findFlightById(@PathVariable Long id){
        FlightSchedule flightSchedule = flightScheduleService.findFlightById(id);
        if (flightSchedule==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(flightSchedule);
    }

    //BHung check email
    @GetMapping("/employee/checkEmail")
    public ResponseEntity<?> checkEmailExisted(@RequestParam String email){
        Account account =  accountService.findAccountByEmail(email);
        if(account==null){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(account);
        }
    }

    //BHung lưu trans và pass
    @PostMapping("/transPass/save")
    public ResponseEntity<?> saveTransactionsAndPassengers(@RequestBody TransactionPassengerDTO transactionPassengerDTO){
        employeeService.saveTransactionsAndTickets(transactionPassengerDTO.getTransactions(),transactionPassengerDTO.getPassengers());
        return ResponseEntity.ok("thành công");
    }

    //BHung tim kiem transaction
    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Transaction transaction = employeeService.findTransactionById(id);
        if (transaction==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(transaction);
    }
}
