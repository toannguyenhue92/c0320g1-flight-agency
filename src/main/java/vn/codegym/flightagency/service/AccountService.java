package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.Employee;

public interface AccountService {
    //CREATE BY ANH DUC
    Account findByEmail(String email);

    //CREATE BY ANH DUC
    void save(Account account);

    //CREATE BY ANH DUC
    Account autoRegAccount(Employee employee);


}
