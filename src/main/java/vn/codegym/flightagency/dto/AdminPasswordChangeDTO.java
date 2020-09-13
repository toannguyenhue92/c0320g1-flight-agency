package vn.codegym.flightagency.dto;

import lombok.Data;


@Data
public class AdminPasswordChangeDTO {
    private Long id;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String backendMessage;

}
