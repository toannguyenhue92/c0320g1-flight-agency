package vn.codegym.flightagency.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
    private Long id;
    private String fullName;
    private Date birthday;
    private String gender;
    private String email;
    private String phoneNumber;
    private String position;
}
