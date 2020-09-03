package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "aircrafts")
@Getter
@Setter
@NoArgsConstructor
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Long id;

    @Column(name = "aircraft_number")
    private String number;

    @Column(name = "aircraft_capacity")
    private Short capacity;
}
