package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    //CREATE BY ANH DUC
    List<Employee> findAll();

    //CREATE BY ANH DUC
    Optional<Employee> findById(Long id);

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

}
