package vn.codegym.flightagency.service;

import org.springframework.security.core.userdetails.UserDetails;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;

public interface AccountService {
    //Created by: Quân
    boolean existsEmail(String email);
    //Created by: Quân
    Account findByEmail(String email);
    //Created by: Quân
    Account saveAccount(Account account);
    //Created by: Quân
    UserDetails getUserDetail(Account account);
    //Created by: Quân
    Account getProfileGoogle(TokenDto tokenDto);
    //Created by: Quân
    Account getProfileFacebook(TokenDto tokenDto);

    //CREATE BY ANH DUC
    Account autoRegAccount(Account account);
}
