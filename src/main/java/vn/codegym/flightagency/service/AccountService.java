package vn.codegym.flightagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;

@Service("AccountService")
public class AccountService implements UserDetailsService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account getAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    public void savingAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

}
