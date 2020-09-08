package vn.codegym.flightagency.service;

import org.springframework.security.core.userdetails.UserDetails;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;

import java.util.Optional;

public interface AccountService {
    boolean existsEmail(String email);

    Account findByEmail(String email);

    Account saveAccount(Account account);

    UserDetails getUserDetail(Account account);

    Account getProfileGoogle(TokenDto tokenDto);
    Account getProfileFacebook(TokenDto tokenDto);
}
