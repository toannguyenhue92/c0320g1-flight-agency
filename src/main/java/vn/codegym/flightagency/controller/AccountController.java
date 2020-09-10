package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.dto.employeeInfoDto;
import vn.codegym.flightagency.service.AccountService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //creator: Mậu
    @GetMapping("employee/{id}")
    public ResponseEntity<employeeInfoDto> findEmployeeById(@PathVariable Long id) {
        employeeInfoDto employeeInfoDto = accountService.findEmployeeInfoDtoById(id);
        if (employeeInfoDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(employeeInfoDto);
    }

    //creator: Mậu
    @PutMapping("employee/changePassword/{id}")
    public ResponseEntity<employeeInfoDto> changePassword(@PathVariable Long id, @RequestBody employeeInfoDto employeeInfoDto) {
        Account account = accountService.findEmployeeById(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.changePassword(employeeInfoDto);
        return new ResponseEntity<>(employeeInfoDto, HttpStatus.OK);
    }


}
