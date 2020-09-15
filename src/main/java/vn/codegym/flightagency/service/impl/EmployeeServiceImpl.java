package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.AccountDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.repository.AccountRepository;
import vn.codegym.flightagency.service.EmployeeService;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //CREATE BY ANH DUC
    @Autowired
    private AccountRepository employeeRepository;

    //CREATE BY ANH DUC
    @Override
    public List<Account> findAll() {

        return employeeRepository.findAll();
    }
    //CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllAccount(Pageable pageable) {
        Page<Account> accounts = employeeRepository.findAll(pageable);
        return transferToDTO(accounts);
    }

    //CREATE BY ANH DUC
    @Override
    public Optional<Account> findById(Long id) {
        return employeeRepository.findById(id);
    }

    //    CREATE BY ANH DUC
    @Override
    public Account findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllByFullName(String name, Pageable pageable) {
        return transferToDTO(employeeRepository.findAllByFullName(name, pageable));
    }

    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllByBirthday(LocalDate birthday, Pageable pageable) {
        return transferToDTO(employeeRepository.findAllByBirthDate(birthday, pageable));
    }

    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllByPhoneNumber(String phone, Pageable pageable) {
        return transferToDTO(employeeRepository.findAllByPhoneNumber(phone, pageable));
    }


    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllByGender(String gender, Pageable pageable) {
        return transferToDTO(employeeRepository.findAllByGender(gender, pageable));
    }

    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> findAllByEmail(String email, Pageable pageable) {
        return transferToDTO(employeeRepository.findAllByEmail(email, pageable));
    }

    //    CREATE BY ANH DUC
    @Override
    public Boolean checkEmailAlready(String email) {
        Account account = employeeRepository.findByEmail(email);
        return account != null;
    }

    //CREATE BY ANH DUC
    @Override
    public void save(Account account) {
        employeeRepository.save(account);
    }

    //CREATE BY ANH DUC
    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }

    //CREATE BY ANH DUC
    @Override
    public AccountDTO coverAccountToEmpDTO(Account employee) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(employee.getId());
        accountDTO.setFullName(employee.getFullName());
        accountDTO.setBirthday(employee.getBirthDate());
        accountDTO.setGender(employee.getGender());
        accountDTO.setEmail(employee.getEmail());
        accountDTO.setPhoneNumber(employee.getPhoneNumber());
        accountDTO.setAvatarImageUrl(employee.getAvatarImageUrl());
        return accountDTO;
    }

    //CREATE BY ANH DUC
    @Override
    public Account coverEmpDTOToAccount(AccountDTO accountDTO) {
        Account employee = new Account();
        employee.setId(accountDTO.getId());
        employee.setFullName(accountDTO.getFullName());
        employee.setBirthDate(accountDTO.getBirthday());
        employee.setGender(accountDTO.getGender());
        employee.setEmail(accountDTO.getEmail());
        employee.setPhoneNumber(accountDTO.getPhoneNumber());
        employee.setAvatarImageUrl(accountDTO.getAvatarImageUrl());
        return employee;
    }

    //    CREATE BY ANH DUC
    @Override
    public Page<AccountDTO> coverListAccountToListEmpDTO(Page<Account> accounts) {
        return null;
    }

    public Page<AccountDTO> transferToDTO(Page<Account> Accounts) {
        Iterator iterator = Accounts.iterator();
        List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
        while (iterator.hasNext()) {
            Account account = (Account) iterator.next();
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(account.getId());
            accountDTO.setFullName(account.getFullName());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setGender(account.getGender());
            accountDTO.setAvatarImageUrl(account.getAvatarImageUrl());
            accountDTO.setBirthday(account.getBirthDate());
            accountDTO.setPhoneNumber(account.getPhoneNumber());
            accountDTOList.add(accountDTO);
        }
        return new PageImpl<AccountDTO>(accountDTOList, Accounts.getPageable(), Accounts.getTotalElements());
    }

    //    ModelMapper modelMapper = new ModelMapper();
//    Order order = modelMapper.map(orderDto, Order.class);
    //    CREATE BY ANH DUC
    @Override
    public List<Account> coverListEmpDTOToListAccount(List<AccountDTO> accountDTOList) {
        List<Account> accounts = new ArrayList<Account>();
        accountDTOList.forEach((n) -> accounts.add(coverEmpDTOToAccount(n)));
        return accounts;
    }


}
