package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Created by Toàn
    @GetMapping(path = "/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Created by Toàn
    @GetMapping(path = "/transaction/unpaid/{accountId}")
    public ResponseEntity<List<Transaction>> getUnpaidTransaction(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.findUnpaidTransactionByAccount(accountId);
        if (transactions != null) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Created by Toàn
    @PatchMapping(path = "/transaction/{id}/pay")
    public ResponseEntity<Transaction> payTransaction(
            @PathVariable Long id, @RequestParam(defaultValue = "") String taxCode) {
        Transaction transaction = transactionService.payTransaction(id, taxCode);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Created by Toàn
    @PatchMapping(path = "/transaction/pay-list")
    public ResponseEntity<List<Transaction>> payTransactions(@RequestBody List<Long> ids,
                                                             @RequestParam(defaultValue = "") String taxCode) {
        List<Transaction> transactions = transactionService.payTransactions(ids, taxCode);
        if (transactions != null) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Created by Toàn
    @GetMapping(path = "/transaction/find-reservation")
    public ResponseEntity<Transaction> findByIdAndPhone(@RequestParam Long id, @RequestParam String phone) {
        Transaction transaction = transactionService.findByIdAndPhone(id, phone);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Created by Toàn
    @PatchMapping(path = "/transaction/{id}/cancel")
    public ResponseEntity<Transaction> cancelTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.cancelTransaction(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
