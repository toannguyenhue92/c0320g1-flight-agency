package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //    CREATE BY ANH DUC
    public Employee findByEmail(String email);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByFullName(String name, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByBirthday(Date birthday, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByPhoneNumber(String phone, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByPosition(String position, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Employee> findAllByGender(String gender, Pageable pageable);

}