package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.CheckinDto;
import vn.codegym.flightagency.model.TransactionDetail;
import vn.codegym.flightagency.service.TransactionDetailService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TransactionDetailController {
    @Autowired
    TransactionDetailService transactionDetailService;

    // Thành Long
    @GetMapping("/customer/checkin")
    public ResponseEntity<Page<CheckinDto>> searchTransactionDetail(@RequestParam(name = "bookingCode", defaultValue = "") String bookingCode,
                                                                   @RequestParam(name = "fullName", defaultValue = "") String fullName,
                                                                   @RequestParam(name = "page") int page) {
        Specification<TransactionDetail> specs = transactionDetailService.getFilter(bookingCode, fullName);
        Page<CheckinDto> transactionDetails;
        transactionDetails = transactionDetailService.findTransactionDetailByCriteria(specs, page);

        if (transactionDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactionDetails);
    }
}
