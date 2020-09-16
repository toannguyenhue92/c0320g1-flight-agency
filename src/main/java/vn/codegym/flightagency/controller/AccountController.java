package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.AccountEmployeeEditDto;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.service.AccountService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //B-HoangLong
    @GetMapping(value = "employee/list-employee",params = {"page", "size"})
    public ResponseEntity<Page<Account>> GetAllEmployee(@RequestParam("page") int page,
                                                        @RequestParam("size") int size) {
        Page<Account> employees = this.accountService.getAllEmployee(PageRequest.of(page, size));
        if (employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //B-HoangLong
    @GetMapping("employee/edit-employee/{id}")
    public ResponseEntity<AccountEmployeeEditDto> getEmployeeById(@PathVariable Long id) {
        AccountEmployeeEditDto employee = this.accountService.findEmployeeDtoById(id);
        if (employee == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AccountEmployeeEditDto>(employee, HttpStatus.OK);
    }

    //B-HoangLong
    @PutMapping("employee/delete-in-list/{id}")
    public ResponseEntity<Account> deleteEmployeeById(@PathVariable Long id) {
        Account employeeDelete = this.accountService.findEmployeeById(id);
        if (employeeDelete == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        employeeDelete.setStatus(true);
        this.accountService.saveEmployee(employeeDelete);
        return new ResponseEntity<>(employeeDelete, HttpStatus.OK);
    }

    //B-HoangLong
    @PutMapping("employee/edit-in-list/{id}")
    public ResponseEntity<AccountEmployeeEditDto> updateUser(@PathVariable Long id, @RequestBody AccountEmployeeEditDto employeeEditDto) {
        this.accountService.editEmployee(employeeEditDto,id);
        return new ResponseEntity<AccountEmployeeEditDto>(employeeEditDto, HttpStatus.OK);
    }
}
