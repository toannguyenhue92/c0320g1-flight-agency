package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.Account;

public interface AccountService {

    //BHung
    Account findAccountByEmail(String email);
}
