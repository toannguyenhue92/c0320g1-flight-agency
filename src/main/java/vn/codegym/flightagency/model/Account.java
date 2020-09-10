package vn.codegym.flightagency.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email ;
    private String password ;
    private String role;
    @OneToOne(mappedBy = "account")
    private Employee employee;
}
