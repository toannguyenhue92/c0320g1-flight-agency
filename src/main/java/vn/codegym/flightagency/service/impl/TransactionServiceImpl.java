package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
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



}
