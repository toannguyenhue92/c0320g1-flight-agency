package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;

import java.time.LocalDateTime;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    // C-Ngan
    Sort sort = Sort.by(Sort.Direction.ASC, "createdTime");
    Pageable page = PageRequest.of(0, 2, sort);
    @Override
    public Page<Transaction> getTransactionsByAccountId(Long accountId, int currentPage) {
        if (currentPage > 0 ){
            Pageable page = PageRequest.of(--currentPage, 2, sort);
            return transactionRepository.findByAccount_IdIs(accountId, page);
        }
        return transactionRepository.findByAccount_IdIs(accountId, page);
    }





    // Creator: Duy
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Creator: Duy
    // create transaction
    @Override
    public Transaction createTransaction(Long flightScheduleId, Long accountId, Double totalPrice) {
        Transaction transaction = new Transaction();
        FlightSchedule flightSchedule = new FlightSchedule();
        LocalDateTime createdDateTime = LocalDateTime.now();
        LocalDateTime dueDateTime = createdDateTime.plusHours(2);
        Account account = new Account();
        account.setId(accountId);
        flightSchedule.setId(flightScheduleId);
        transaction.setFlightSchedule(flightSchedule);
        transaction.setAccount(account);
        transaction.setPrice(totalPrice);
        transaction.setStatus("chờ thanh toán");
        transaction.setCreatedTime(createdDateTime);
        transaction.setDueTime(dueDateTime);
        return transaction;
    }

}
