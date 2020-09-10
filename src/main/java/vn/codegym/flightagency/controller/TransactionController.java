package vn.codegym.flightagency.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.exception.ViolatedException;
import vn.codegym.flightagency.service.TransactionDetailService;
import vn.codegym.flightagency.service.TransactionService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    private TransactionDetailService transactionDetailService;

    // Creator: Duy
    // Find flight schedule
    @PostMapping("/transaction/booking")
    public ResponseEntity<?> makeBooking(@RequestBody BookingDTO booking)
            throws ViolatedException {
        transactionDetailService.saveTransactionDetail(booking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
