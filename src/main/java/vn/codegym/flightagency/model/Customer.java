package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_full_name")
    private String fullName;

    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "flight_route")
    private String flightRoute;

    @Column(name = "date_of_flight")
    private LocalDateTime dateOfFlight;

    @Column(name = "total_money")
    private Double totalMoney;

    @ManyToOne
    @JoinColumn(name = "checkin_status_id")
    private CheckinStatus checkinStatus;

}
