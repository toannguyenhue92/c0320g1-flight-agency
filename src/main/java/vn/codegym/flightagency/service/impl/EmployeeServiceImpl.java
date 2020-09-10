package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.model.Employee;
import vn.codegym.flightagency.repository.EmployeeRepository;
import vn.codegym.flightagency.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    //CREATE BY ANH DUC
    @Autowired
    private EmployeeRepository employeeRepository;

    //CREATE BY ANH DUC
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    //CREATE BY ANH DUC
    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    //CREATE BY ANH DUC
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    //CREATE BY ANH DUC
    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }

    //CREATE BY ANH DUC
    @Override
    public EmployeeDTO coverEmpToEmpDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFullName(employee.getFullName());
        employeeDTO.setBirthday(employee.getBirthday());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setPosition(employee.getPosition());
        return employeeDTO;
    }

    //CREATE BY ANH DUC
    @Override
    public Employee coverEmpDTOToEmp(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFullName(employeeDTO.getFullName());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setGender(employeeDTO.getGender());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setPosition(employeeDTO.getPosition());
        return employee;
    }

    @Override
    public List<EmployeeDTO> coverListEmpToListEmpDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        employees.forEach((n) -> employeeDTOList.add(coverEmpToEmpDTO(n)));
        return employeeDTOList;
    }

    @Override
    public List<Employee> coverListEmpDTOToListEmp(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = new ArrayList<Employee>();
        employeeDTOList.forEach((n) -> employees.add(coverEmpDTOToEmp(n)));
        return employees;
    }
}
