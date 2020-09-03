package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
