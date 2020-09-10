package vn.codegym.flightagency.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.CustomerChangePasswordDTO;
import vn.codegym.flightagency.dto.CustomerUpdateDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //    Created By Thiện
    @Override
    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    //    Created By Thiện
    @Override
    public CustomerUpdateDTO findCustomerUpdateDTOById(Long id) {
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO();
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null ) {
            customerUpdateDTO.setFullName(account.getFullName());
            customerUpdateDTO.setEmail(account.getEmail());
            customerUpdateDTO.setGender(account.getGender());
            customerUpdateDTO.setPhoneNumber(account.getPhoneNumber());
            customerUpdateDTO.setBirthday(account.getBirthDate());
            customerUpdateDTO.setAvatarImageUrl(account.getAvatarImageUrl());
            customerUpdateDTO.setAddress(account.getAddress());
            customerUpdateDTO.setRank(account.getCustomerRank());
            return customerUpdateDTO;
        }
        return null ;
    }

    //    Created By Thiện
    @Override
    public void updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        Account account = accountRepository.findById(customerUpdateDTO.getId()).orElse(null);
        assert account != null;
        account.setFullName(customerUpdateDTO.getFullName().trim());
        account.setAddress(customerUpdateDTO.getAddress().trim());
        account.setGender(customerUpdateDTO.getGender());
        account.setPhoneNumber(customerUpdateDTO.getPhoneNumber());
        account.setBirthDate(customerUpdateDTO.getBirthday());
        account.setAvatarImageUrl(customerUpdateDTO.getAvatarImageUrl());
        List<String> messages = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        for (Account testAcc : accounts) {
            if (!account.getEmail().equals(customerUpdateDTO.getEmail().trim()) && testAcc.getEmail().equals(customerUpdateDTO.getEmail().trim())) {
                messages.add("Email này đã được đăng kí. Vui lòng nhập lại email khác.");
                break;
            }
        }
        account.setEmail(customerUpdateDTO.getEmail().trim());
        for (Account testAcc : accounts) {
            if (!account.getPhoneNumber().equals(customerUpdateDTO.getPhoneNumber()) && testAcc.getPhoneNumber().equals(customerUpdateDTO.getPhoneNumber())) {
                messages.add("Số điện thoại này đã được đăng kí. Vui lòng nhập lại số điện thoại khác.");
                break;
            }
        }
        account.setPhoneNumber(customerUpdateDTO.getPhoneNumber());
        customerUpdateDTO.setBackendMessage(messages);
        if (customerUpdateDTO.getBackendMessage().size() == 0) {
            accountRepository.save(account);
        }
    }

    //    Created By Thiện
    @Override
    public void changePassword(CustomerChangePasswordDTO customerChangePasswordDTO) {
        Account account = accountRepository.findById(customerChangePasswordDTO.getId()).orElse(null);
        assert account != null;
        List<String> messages = new ArrayList<>();
        if (!customerChangePasswordDTO.getPassword().equals("")) {
            if (!customerChangePasswordDTO.getNewPassword().equals("")) {
                if (BCrypt.checkpw(customerChangePasswordDTO.getPassword(), account.getPassword())) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    account.setPassword(encoder.encode(customerChangePasswordDTO.getNewPassword()));
                } else {
                    messages.add("Mật khẩu hiện tại không đúng. Xin vui lòng nhập lại.");
                }
            } else {
                messages.add("Vui lòng nhập mật khẩu hiện tại đi kèm với mật khẩu mới và xác nhận mật khẩu.");
            }
        } else if (!customerChangePasswordDTO.getNewPassword().equals("")) {
            messages.add("Vui lòng nhập mật khẩu hiện tại khi đổi mật khẩu.");
        }
        customerChangePasswordDTO.setBackendMessage(messages);
        if (customerChangePasswordDTO.getBackendMessage().size() == 0) {
            accountRepository.save(account);
        }
    }
}
