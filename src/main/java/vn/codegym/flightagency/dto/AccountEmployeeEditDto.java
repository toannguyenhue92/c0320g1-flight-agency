package vn.codegym.flightagency.dto;
import lombok.Data;

import java.time.LocalDate;

//B-HoangLong
@Data
public class AccountEmployeeEditDto {
    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private String phoneNumber;
}