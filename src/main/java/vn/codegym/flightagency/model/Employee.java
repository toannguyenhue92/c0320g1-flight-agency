package vn.codegym.flightagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date birthday;
    private String gender;
    private String email;
    private String phoneNumber;
    private String position;
    private Boolean status;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    @JsonProperty
    public void setAccount(Account account) {
        this.account = account;
    }
}
