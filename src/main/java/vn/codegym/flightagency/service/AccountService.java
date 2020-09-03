package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.Account;

public interface AccountService {
    Account findByEmail(String email);

    void save(Account account);

}
