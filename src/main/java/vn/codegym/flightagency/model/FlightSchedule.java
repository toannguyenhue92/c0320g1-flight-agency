package vn.codegym.flightagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flight_schedules")
@Getter
@Setter
@NoArgsConstructor
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_schedule_id")
    private Long id;

    @Column(name = "flight_date",nullable = false)
    private LocalDateTime flightDate;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @OneToMany(mappedBy = "flightSchedule")
    @JsonIgnoreProperties(value = "flightSchedule")
    private Set<Transaction> transactions;


}
