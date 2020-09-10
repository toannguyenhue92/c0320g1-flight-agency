package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    //BHung
    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
