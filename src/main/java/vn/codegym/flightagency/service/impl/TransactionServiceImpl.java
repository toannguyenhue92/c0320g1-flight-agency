package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.PassengerService;
import vn.codegym.flightagency.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


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
        LocalDateTime dueDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(createdDateTime.getHour() + 2, createdDateTime.getMinute()));
        Account account = new Account();
        account.setId(accountId);
        flightSchedule.setId(flightScheduleId);
        transaction.setFlightSchedule(flightSchedule);
        transaction.setAccount(account);
        transaction.setPrice(totalPrice);
        transaction.setStatus("Chờ thanh toán");
        transaction.setCreatedTime(createdDateTime);
        transaction.setDueTime(dueDateTime);
        return transaction;
    }

}
