package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.AccountEmployeeDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<AccountEmployeeDto> getAllEmployee(Pageable pageable) {

        Page<Account> accountPage = this.accountRepository.findAllByStatusFalseAndRoleContains( pageable,"ROLE_EMPLOYEE");
        Page<AccountEmployeeDto> dtoPage = accountPage.map(new Converter<Account,AccountEmployeeDto>()){

        return null;
    }
}
