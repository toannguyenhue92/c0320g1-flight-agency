package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.dto.AccountEmployeeDto;

public interface AccountService {
    Page<AccountEmployeeDto> getAllEmployee(Pageable pageable);
}
