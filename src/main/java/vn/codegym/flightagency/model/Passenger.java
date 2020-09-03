package vn.codegym.flightagency.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "passenger")
    private Transaction transaction;

}
