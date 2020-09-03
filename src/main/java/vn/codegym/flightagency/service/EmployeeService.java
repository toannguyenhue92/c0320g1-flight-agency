package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    void save(Employee employee);

    void remove(Long id);
}
