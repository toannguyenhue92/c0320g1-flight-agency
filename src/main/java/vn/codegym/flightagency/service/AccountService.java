package vn.codegym.flightagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.AdminPasswordChangeDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

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

    public void changePassword(AdminPasswordChangeDTO passwordChangeDTO) {
        Account account = accountRepository.findById(passwordChangeDTO.getId()).orElse(null);
        assert account != null;
        String messages = "";
        if (!passwordChangeDTO.getPassword().equals("")) { //đã nhập password cũ
            if (!passwordChangeDTO.getNewPassword().equals("")) { //đã nhập password mới
                if (BCrypt.checkpw(passwordChangeDTO.getNewPassword(), account.getPassword())) { // check nhập password cũ đã đúng chưa
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // encode password mới
                    account.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));// set password mới vào account
                }else {
                    messages = "Mật khẩu hiện tại không đúng .Xin vui lòng nhập lại!"; // password check không đúng
                }
            } else {
                messages = "Vui lòng nhập mật khẩu hiện tại đi kèm mật khẩu mới và xác nhận mật khẩu"; // check lỗi chưa nhập password mới
            }
        } else if (!passwordChangeDTO.getNewPassword().equals("")) {
            messages = "Vui lòng nhập mật khẩu hiện tại"; // chưa nhập password cũ
        }
        passwordChangeDTO.setBackendMessage(messages);
        if (passwordChangeDTO.getBackendMessage().equals("")) {
            accountRepository.save(account);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

}
