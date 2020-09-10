package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.dto.ResponseDTO;
import vn.codegym.flightagency.model.Employee;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
//CREATE BY ANH DUC
public class EmployeeAPI {
    //CREATE BY ANH DUC
    @Autowired
    private EmployeeService employeeService;
    //CREATE BY ANH DUC
    @Autowired
    private AccountService accountService;

    //CREATE BY ANH DUC
    @GetMapping("/employees")
    public ResponseEntity<? extends Object> getAllEmployee() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        employeeDTOList = employeeService.coverListEmpToListEmpDTO(employeeService.findAll());
        ResponseDTO response = new ResponseDTO();
        if (employeeDTOList.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Data Null");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Get data Thành Công");
        response.setBody(employeeDTOList);
        return new ResponseEntity<Object>(response, response.getStatus());

    }

    //CREATE BY ANH DUC
    @GetMapping("/employees/{id}")
    public ResponseEntity<? extends Object> getEmployeeById(@PathVariable("id") Long id) {
        ResponseDTO response = new ResponseDTO();
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User Id :" + id + " không tồn tại!");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Get data Thành Công");
        response.setBody(employee);
        return new ResponseEntity<Object>(response, response.getStatus());
    }

    //CREATE BY ANH DUC
    @PostMapping("employees/save")
    public ResponseEntity<? extends Object> saveEmployees(@RequestBody List<EmployeeDTO> employeeDTOList) {
        ResponseDTO response = new ResponseDTO();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeService.coverListEmpDTOToListEmp(employeeDTOList);

        if (employees == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Lưu dữ liệu thất bại");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        employees.forEach((n) -> {
            n.setAccount(accountService.autoRegAccount(n));
            employeeService.save(n);
        });
        response.setStatus(HttpStatus.OK);
        response.setMessage("Lưu dữ liệu thành công");
        return new ResponseEntity<Object>(response, response.getStatus());
    }

}
