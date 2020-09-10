package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.CustomerChangePasswordDTO;
import vn.codegym.flightagency.dto.CustomerUpdateDTO;
import vn.codegym.flightagency.model.Account;

public interface AccountService {
    //    Created By Thiện
    Account findAccountById(Long id) ;

    //    Created By Thiện
    CustomerUpdateDTO findCustomerUpdateDTOById(Long id) ;

    //    Created By Thiện
    void updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    //    Created By Thiện
    void changePassword(CustomerChangePasswordDTO customerChangePasswordDTO);
}
