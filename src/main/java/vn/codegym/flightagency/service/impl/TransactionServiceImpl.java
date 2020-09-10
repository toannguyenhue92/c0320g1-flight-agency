package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.BillRepository;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;
import vn.codegym.flightagency.service.PassengerService;
import vn.codegym.flightagency.utility.BillCodeGenerator;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private BillRepository billRepository;

    // Created by Toàn
    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    // Created by Toàn
    @Override
    public List<Transaction> findUnpaidTransactionByAccount(Long accountId) {
        List<Transaction> transactions = transactionRepository.findUnpaidByAccountId(accountId);
        if (transactions.size() > 0) {
            return transactions;
        } else {
            return null;
        }
    }

    // Created by Toàn
    @Override
    public Transaction findByIdAndPhone(Long id, String phone) {
        Optional<Transaction> optional = transactionRepository.findByIdAndPhone(id, phone);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            optional = transactionRepository.findById(id);
            if (optional.isPresent()) {
                Transaction transaction = optional.get();
                for (Passenger p : transaction.getPassengers()) {
                    if (phone.equals(p.getPhoneNumber())) {
                        return transaction;
                    }
                }
            }
            return null;
        }
    }

    // Created by Toàn
    @Override
    public Transaction payTransaction(Long id, String taxCode) {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Transaction transaction = optional.get();
        transaction.setStatus(TransactionService.PAID);
        Bill bill = new Bill();
        bill.setDateCreated(LocalDateTime.now());
        bill.setTransaction(transaction);
        bill.setTaxCode(taxCode);
        bill = billRepository.save(bill);
        bill.setBillCode(BillCodeGenerator.generate(bill.getId()));
        billRepository.save(bill);
        return transactionRepository.save(transaction);
    }

    // Created by Toàn
    @Override
    public Transaction cancelTransaction(Long id) {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Transaction transaction = optional.get();
        transaction.setStatus(TransactionService.CANCELED);
        return transactionRepository.save(transaction);
    }

    // Created by Toàn
    @Override
    public List<Transaction> payTransactions(List<Long> ids, String taxCode) {
        List<Transaction> transactions = new LinkedList<>();
        for (Long id : ids) {
            Transaction transaction = payTransaction(id, taxCode);
            if (transaction != null) {
                transactions.add(transaction);
            } else {
                return null;
            }
        }
        return transactions.size() > 0 ? transactions : null;
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
