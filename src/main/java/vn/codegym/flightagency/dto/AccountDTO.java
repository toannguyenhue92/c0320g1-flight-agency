package vn.codegym.flightagency.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AccountDTO {
    private Long id;
    private String fullName;
    private LocalDate birthday;
    private String gender;
    private String email;
    private String phoneNumber;
    private String avatarImageUrl;
    private List<String> backendMessage;
}
