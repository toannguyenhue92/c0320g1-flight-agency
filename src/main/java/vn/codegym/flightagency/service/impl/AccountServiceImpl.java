package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.TokenDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.security.JwtTokenUtil;
import vn.codegym.flightagency.service.AccountService;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${secretPsw}")
    String secretPsw;
    @Autowired(required = false)
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Override
    public boolean existsEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmailAndStatusIsTrue(email);
    }

    @Override
    public Account saveAccount(String email) {
        Account account = new Account(email, passwordEncoder.encode(secretPsw), "ROLE_USER", true);
        return accountRepository.save(account);
    }

    @Override
    public TokenDto login(Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), secretPsw)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setValue(jwtToken);
        return tokenDto;
    }
}

