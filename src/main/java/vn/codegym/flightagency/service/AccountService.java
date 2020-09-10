package vn.codegym.flightagency.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.dto.employeeInfoDto;

public interface AccountService {
    //creator: Mậu
    employeeInfoDto findEmployeeInfoDtoById(Long id);

    //creator: Mậu
    Account findEmployeeById(Long id);

    //creator: Mậu
    void changePassword(employeeInfoDto employeeInfoDto);
}
