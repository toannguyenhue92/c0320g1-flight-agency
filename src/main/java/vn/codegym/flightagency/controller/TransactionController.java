package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.exception.ViolatedException;
import vn.codegym.flightagency.model.Branch;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.service.EmailService;
import vn.codegym.flightagency.service.TransactionDetailService;
import vn.codegym.flightagency.service.TransactionService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @Autowired
    private EmailService emailService;
    // Creator: Duy
    // Find flight schedule
    @PostMapping("/transaction/booking")
    public ResponseEntity<?> makeBooking(@RequestBody BookingDTO booking)
            throws ViolatedException {
        transactionDetailService.saveTransactionDetail(booking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/test-email")
//    public ResponseEntity<?> sendMail() {
//        FlightSchedule flight = new FlightSchedule();
//        Branch branch = new Branch();
//        branch.setId(2l);
//        branch.setName("BambooAir");
//        flight.setBranch(branch);
//        emailService.sendBookingCode(12l, flight, "nghoangduy1541996@gmail.com");
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
