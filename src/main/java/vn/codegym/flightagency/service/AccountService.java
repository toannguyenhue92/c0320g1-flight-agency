package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;

import java.util.Optional;

public interface AccountService {
    boolean existsEmail(String email);

    Optional<Account> findByEmail(String email);

    Account saveAccount(String email);

    TokenDto login(Account account);
}
