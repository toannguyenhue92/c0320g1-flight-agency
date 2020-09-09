package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.BillRepository;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;
import vn.codegym.flightagency.utility.BillCodeGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BillRepository billRepository;

    // Created by Toàn
    @Override
    public List<Transaction> findUnpaidTransaction(Long accountId) {
        List<Transaction> transactions = transactionRepository.findUnpaidByAccountId(accountId);
        if (transactions.size() > 0) {
            return transactions;
        } else {
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
        transaction.setStatus("Đã thanh toán");
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
}
