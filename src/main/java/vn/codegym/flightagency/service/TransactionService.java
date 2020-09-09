package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    // Created by Toàn
    List<Transaction> findUnpaidTransaction(Long accountId);

    // Created by Toàn
    Transaction payTransaction(Long id, String taxCode);

    // Created by Toàn
    Transaction findByIdAndPhone(Long id, String phone);
}
