package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;

@RestController
@RequestMapping ("/api/v1")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionRepository transactionRepository;
    // C-Ngan
    @GetMapping("customer/transactions/{accountId}")
    public ResponseEntity<Page<Transaction>> getTransactionsByAccountId(@PathVariable Long accountId,
                                                                        @RequestParam(name = "page", defaultValue = "0", required = false) int page) {
        System.out.println(page);
        Page<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId, page);
        if (transactions.isEmpty()) return new ResponseEntity<Page<Transaction>>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(transactions);
    }










}
