package vn.codegym.flightagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flight_schedules")
@Getter
@Setter
@NoArgsConstructor
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column(name = "departure_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_location")
    private String arrivalLocation;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalDateTime;

    @Column(name = "brand")
    private String brand;

    @Column(name = "air_craft_code")
    private String airCraftCode;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "flightSchedule")
    @JsonIgnore
    private List<Transaction> transactions;
}
