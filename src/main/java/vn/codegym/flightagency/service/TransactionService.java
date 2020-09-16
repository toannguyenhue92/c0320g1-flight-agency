package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import vn.codegym.flightagency.model.Transaction;

public interface TransactionService {
    // C-Ngan
    Page<Transaction> getTransactionsByAccountId(Long accountId, int currentPage);
    // Creator: Duy
    Transaction save(Transaction transaction);

    //Creator: Duy
    Transaction createTransaction(Long flightScheduleId, Long accountId, Double totalPrice);

    //Creator: Duy
    boolean checkFull(Long id, int persons);
}
