package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.model.Employee;
import vn.codegym.flightagency.repository.EmployeeRepository;
import vn.codegym.flightagency.service.EmployeeService;

import java.util.ArrayList;
import java.util.Date;
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
    //    CREATE BY ANH DUC
    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    //    CREATE BY ANH DUC
    @Override
    public Page<Employee> findAllByFullName(String name, Pageable pageable) {
        return null;
    }
    //    CREATE BY ANH DUC
    @Override
    public Page<Employee> findAllByBirthday(Date birthday, Pageable pageable) {
        return employeeRepository.findAllByBirthday(birthday, pageable);
    }
    //    CREATE BY ANH DUC
    @Override
    public Page<Employee> findAllByPhoneNumber(String phone, Pageable pageable) {
        return employeeRepository.findAllByPhoneNumber(phone, pageable);
    }
    //    CREATE BY ANH DUC
    @Override
    public Page<Employee> findAllByPosition(String position, Pageable pageable) {
        return employeeRepository.findAllByPosition(position, pageable);
    }
    //    CREATE BY ANH DUC
    @Override
    public Page<Employee> findAllByGender(String gender, Pageable pageable) {
        return employeeRepository.findAllByGender(gender, pageable);
    }
    //    CREATE BY ANH DUC
    @Override
    public Boolean checkEmailAlready(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return employee != null;
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
    //    CREATE BY ANH DUC
    @Override
    public List<EmployeeDTO> coverListEmpToListEmpDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        employees.forEach((n) -> employeeDTOList.add(coverEmpToEmpDTO(n)));
        return employeeDTOList;
    }
    //    CREATE BY ANH DUC
    @Override
    public List<Employee> coverListEmpDTOToListEmp(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = new ArrayList<Employee>();
        employeeDTOList.forEach((n) -> employees.add(coverEmpDTOToEmp(n)));
        return employees;
    }


}
