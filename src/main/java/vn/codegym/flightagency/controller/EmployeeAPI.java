package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.EmployeeDTO;
import vn.codegym.flightagency.dto.ResponseDTO;
import vn.codegym.flightagency.model.Employee;
import vn.codegym.flightagency.service.AccountService;
import vn.codegym.flightagency.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.*;

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
            response.setMessage("Nhân viên Id :" + id + " không tồn tại!");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Get data Thành Công");
        response.setBody(employee);
        return new ResponseEntity<Object>(response, response.getStatus());
    }

    //CREATE BY ANH DUC
    @PostMapping("/employees")
    public ResponseEntity<? extends Object> saveEmployees(@RequestBody List<EmployeeDTO> employeeDTOList) {
        ResponseDTO response = new ResponseDTO();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeService.coverListEmpDTOToListEmp(employeeDTOList);

        if (employees == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Lưu dữ liệu thất bại");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        for (Employee n : employees) {
            if (employeeService.checkEmailAlready(n.getEmail())) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Email Đã tồn tại : " + n.getEmail());
                return new ResponseEntity<Object>(response, response.getStatus());
            }
            n.setAccount(accountService.autoRegAccount(n));
            employeeService.save(n);
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Lưu dữ liệu thành công");
        return new ResponseEntity<Object>(response, response.getStatus());
    }

    @GetMapping("/employees/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<Employee> pageEmployees;
        List<EmployeeDTO> list = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        try {
            switch (key) {
                case "fullname":
                    pageEmployees = employeeService.findAllByFullName(value, paging);
                    break;
                case "birthday":
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                    pageEmployees = employeeService.findAllByBirthday(date1, paging);
                    break;
                case "phone":
                    pageEmployees = employeeService.findAllByPhoneNumber(value, paging);
                    break;
                case "position":
                    pageEmployees = employeeService.findAllByPosition(value, paging);
                    break;
                case "gender":
                    pageEmployees = employeeService.findAllByGender(value, paging);
                    break;

                default:
                    response.put("message", "Tìm kiếm không chính xác !");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (pageEmployees == null) {
                response.put("message", "không có kết quả phù hợp !");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            list = employeeService.coverListEmpToListEmpDTO(pageEmployees.getContent());


            response.put("tutorials", list);
            response.put("currentPage", pageEmployees.getNumber());
            response.put("totalItems", pageEmployees.getTotalElements());
            response.put("totalPages", pageEmployees.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
