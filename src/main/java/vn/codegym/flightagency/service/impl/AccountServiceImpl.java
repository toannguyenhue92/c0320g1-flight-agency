package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.dto.employeeInfoDto;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    //creator: Mậu
    @Override
    public employeeInfoDto findEmployeeInfoDtoById(Long id) {
        employeeInfoDto employeeInfoDtos = new employeeInfoDto();
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            employeeInfoDtos.setFullName(account.getFullName());
            employeeInfoDtos.setGender(account.getGender());
            employeeInfoDtos.setBirthday(account.getBirthDate());
            employeeInfoDtos.setEmail(account.getEmail());
            employeeInfoDtos.setPhoneNumber(account.getPhoneNumber());
            employeeInfoDtos.setAddress(account.getAddress());
            employeeInfoDtos.setAvatarUrl(account.getAvatarImageUrl());
            return employeeInfoDtos;
        }
        return null;
    }

    //creator: Mậu
    @Override
    public Account findEmployeeById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    //creator: Mậu
    @Override
    public void changePassword(employeeInfoDto employeeInfoDto) {
        Account account = accountRepository.findById(employeeInfoDto.getId()).orElse(null);
        assert account != null;
        List<String> messages = new ArrayList<>();
        if (!employeeInfoDto.getPassword().equals("")) {
            if (!employeeInfoDto.getNewPassword().equals("")) {
                if (BCrypt.checkpw(employeeInfoDto.getPassword(), account.getPassword())) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    account.setPassword(encoder.encode(employeeInfoDto.getNewPassword()));
                } else {
                    messages.add("Mật khẩu hiện tại không đúng. Xin vui lòng nhập lại.");
                }
            }
        }
        employeeInfoDto.setBackendMessage(messages);
        if (employeeInfoDto.getBackendMessage().size() == 0) {
            accountRepository.save(account);
        }

    }

}
