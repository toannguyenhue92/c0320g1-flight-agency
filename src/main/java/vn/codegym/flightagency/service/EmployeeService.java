package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.model.Employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    //CREATE BY ANH DUC
    List<Employee> findAll();

    //CREATE BY ANH DUC
    Optional<Employee> findById(Long id);

    //CREATE BY ANH DUC
    Employee findByEmail(String email);

    //CREATE BY ANH DUC
    Page<Employee> findAllByFullName(String name, Pageable pageable);

    //CREATE BY ANH DUC
    Page<Employee> findAllByBirthday(Date birthday, Pageable pageable);

    //CREATE BY ANH DUC
    Page<Employee> findAllByPhoneNumber(String phone, Pageable pageable);

    //CREATE BY ANH DUC
    Page<Employee> findAllByPosition(String position, Pageable pageable);

    //CREATE BY ANH DUC
    Boolean checkEmailAlready(String email);

    //CREATE BY ANH DUC
    void save(Employee employee);

    //CREATE BY ANH DUC
    void remove(Long id);

    //CREATE BY ANH DUC
    EmployeeDTO coverEmpToEmpDTO(Employee employee);

    //CREATE BY ANH DUC
    Employee coverEmpDTOToEmp(EmployeeDTO employeeDTO);

    //CREATE BY ANH DUC
    List<EmployeeDTO> coverListEmpToListEmpDTO(List<Employee> employees);

    //CREATE BY ANH DUC
    List<Employee> coverListEmpDTOToListEmp(List<EmployeeDTO> employeeDTOList);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByGender(String gender, Pageable pageable);
}
