package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;


     @ManyToOne
     @JoinColumn(name = "role_id")
    private Role role;

     @Column(name = "status")
    private boolean status;

     @Column(name="image_url")
    private String imageURL;

    public Account(String email, String password, Role role, boolean status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
