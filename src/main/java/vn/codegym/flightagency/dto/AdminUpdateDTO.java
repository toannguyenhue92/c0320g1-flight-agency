package vn.codegym.flightagency.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AdminUpdateDTO {
    private Long id;
    private String code;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String gender;
    private String phoneNumber;
    private String address;
    private String avatarImageUrl;
    private String role;
    private boolean status = false;
    private List<String> backendMessage;
}
