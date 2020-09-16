package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.AccountEmployeeDto;
import vn.codegym.flightagency.service.AccountService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "employee/list-employee",params = {"page", "size"})
    public ResponseEntity<Page<AccountEmployeeDto>> GetAllEmployee(@RequestParam("page") int page,
                                                                   @RequestParam("size") int size) {
        Page<AccountEmployeeDto> employeeEditDtos = this.accountService.getAllEmployee(PageRequest.of(page, size));
    }
}
