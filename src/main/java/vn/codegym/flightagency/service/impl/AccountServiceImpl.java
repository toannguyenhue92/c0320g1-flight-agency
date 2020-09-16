package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.AccountEmployeeEditDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //B-HoangLong
    @Override
    public Page<Account> getAllEmployee(Pageable pageable) {
        return this.accountRepository.findAllByStatusFalseAndRoleContains( pageable,"ROLE_EMPLOYEE");
    }

    //B-HoangLong
    @Override
    public Account findEmployeeById(Long id) {
        return this.accountRepository.findByIdAndRoleContains(id,"ROLE_EMPLOYEE");
    }

    //B-HoangLong
    @Override
    public void saveEmployee(Account employeeDelete) {
        this.accountRepository.save(employeeDelete);
    }

    //B-HoangLong
    @Override
    public void editEmployee(AccountEmployeeEditDto employeeEditDto, Long id) {
        Account employee = findEmployeeById(id);
        employee.setFullName(employeeEditDto.getFullName());
        employee.setGender(employeeEditDto.getGender());
        employee.setEmail(employeeEditDto.getEmail());
        employee.setPhoneNumber(employeeEditDto.getPhoneNumber());
        employee.setBirthDate(employeeEditDto.getBirthDate());
        this.accountRepository.save(employee);
    }

    //B-HoangLong
    @Override
    public AccountEmployeeEditDto findEmployeeDtoById(Long id) {
        Account employee = this.accountRepository.findByIdAndRoleContains(id,"ROLE_EMPLOYEE");
        return this.changeAccountToAccountEmployeeEditDto(employee);
    }

    //B-HoangLong
    private AccountEmployeeEditDto changeAccountToAccountEmployeeEditDto(Account employee){
        AccountEmployeeEditDto employeeEditDto = new AccountEmployeeEditDto();
        employeeEditDto.setId(employee.getId());
        employeeEditDto.setFullName(employee.getFullName());
        employeeEditDto.setGender(employee.getGender());
        employeeEditDto.setEmail(employee.getEmail());
        employeeEditDto.setPhoneNumber(employee.getPhoneNumber());
        employeeEditDto.setBirthDate(employee.getBirthDate());
        return employeeEditDto;
    }

}
