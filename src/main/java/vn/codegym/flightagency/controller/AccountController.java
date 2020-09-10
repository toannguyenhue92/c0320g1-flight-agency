package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.CustomerChangePasswordDTO;
import vn.codegym.flightagency.dto.CustomerUpdateDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService ;

//    Created By Thiện - Tìm khách hàng theo Id
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerUpdateDTO> findCustomerById(@PathVariable Long id) {
        CustomerUpdateDTO customerUpdateDTO = accountService.findCustomerUpdateDTOById(id);
        if (customerUpdateDTO == null) {
            return new ResponseEntity<CustomerUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(customerUpdateDTO);
    }

    //    Created By Thiện - Chỉnh sửa thông tin khách hàng
    @PutMapping("customer/update/{id}")
    public ResponseEntity<CustomerUpdateDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return new ResponseEntity<CustomerUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        accountService.updateCustomer(customerUpdateDTO);
        return new ResponseEntity<CustomerUpdateDTO>(customerUpdateDTO, HttpStatus.OK);
    }

    //    Created By Thiện - Thay đổi mật khẩu dành cho khách hàng
    @PutMapping("customer/changepass/{id}")
    public ResponseEntity<CustomerChangePasswordDTO> CustomerChangePassword(@PathVariable Long id, @RequestBody CustomerChangePasswordDTO customerChangePasswordDTO) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return new ResponseEntity<CustomerChangePasswordDTO>(HttpStatus.NOT_FOUND);
        }
        accountService.changePassword(customerChangePasswordDTO);
        return new ResponseEntity<CustomerChangePasswordDTO>(customerChangePasswordDTO, HttpStatus.OK);
    }
}
