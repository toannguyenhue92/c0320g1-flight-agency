package vn.codegym.flightagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "booking_date",nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @OneToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_schedule_id")
    @JsonIgnoreProperties(value = "transactions")
    private FlightSchedule flightSchedule;

    @Column(name = "transaction_type", nullable = false)
    private Boolean type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties(value = "transactions")
    private Employee employee;

    @Column(name = "charge", nullable = false)
    private Long charge;

    @Column(name = "discount", nullable = false)
    private Long discount;

    @Column(name = "total", nullable = false)
    private Long total;

}
