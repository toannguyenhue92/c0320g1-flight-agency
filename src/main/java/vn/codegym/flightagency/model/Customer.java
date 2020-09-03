package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "customer_name")
    private String customerName ;

    @Column(name = "customer_email")
    private String customerEmail ;

    @Column(name = "custome_birthday")
    private LocalDate customerBirthday ;

    @Column(name = "customer_phoneNumber")
    private String customerPhoneNumber ;

    @Column(name = "customer_id_card")
    private String customerIdCard ;

    @Column(name = "customer_address")
    private String customerAddress ;

    @Column(name = "customer_gender")
    private String customerGender ;

    @Column(name = "customer_rank")
    private String customerRank ;

    @Column(name = "customer_avatar")
    private String customerAvatar ;

    @Column(name = "customer_isDeleted")
    private Boolean isDeleted ;

}
