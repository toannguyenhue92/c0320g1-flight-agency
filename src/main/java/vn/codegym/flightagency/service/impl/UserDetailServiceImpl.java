package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository userReposiroty;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> users = userReposiroty.findByEmailAndStatusIsTrue(s);
        System.out.println(users);
        if (users.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        String email = users.get().getEmail();
        String password = users.get().getPassword();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String role = users.get().getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(email, password, grantedAuthorities);
    }
}
