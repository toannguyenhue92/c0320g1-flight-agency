package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
}
