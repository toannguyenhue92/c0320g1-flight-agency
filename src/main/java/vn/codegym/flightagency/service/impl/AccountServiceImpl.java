package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.Employee;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.EmailService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private EmailService emailService;
    //CREATE BY ANH DUC
    @Autowired
    private AccountRepository accountRepository;

    //CREATE BY ANH DUC
    @Override
    public Account findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    //CREATE BY ANH DUC
    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    //CREATE BY ANH DUC
    @Override
    public Account autoRegAccount(Employee employee) {
        Account account = new Account();
        account.setEmail(employee.getEmail());
        account.setRole("ROLE_EMPLOYEE");
        account.setPassword("random");
        accountRepository.save(account);
        String to = employee.getEmail();
        String subject = "Chào mừng bạn gia nhập CGB Airlines ";
        String text = " Thông tin tài khoản của bạn \n " +
                "User : " + employee.getEmail() +
                "Password: " + account.getPassword() +
                "Tài khoản này được dùng để đăng nhập vào hệ thống tại http://localhost:4200/employee/login";
        emailService.sendSimpleMessage(to, subject, text);
        return account;
    }
}
