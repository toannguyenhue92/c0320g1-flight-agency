package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.dto.AccountEmployeeEditDto;
import vn.codegym.flightagency.model.Account;

public interface AccountService {
    //B-HoangLong
    Page<Account> getAllEmployee(Pageable pageable);

    //B-HoangLong
    Account findEmployeeById(Long id);

    //B-HoangLong
    void saveEmployee(Account employeeDelete);

    //B-HoangLong
    void editEmployee(AccountEmployeeEditDto employeeEditDto, Long id);

    //B-HoangLong
    AccountEmployeeEditDto findEmployeeDtoById(Long id);
}
