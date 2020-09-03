package vn.codegym.flightagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long id;

    @Column(name = "customer_fullname")
    private String fullName;

    @Column(name = "customer_birthday")
    private LocalDateTime birthday;

    @Column(name = "customer_gender")
    private String gender;

    @Column(name = "customer_email")
    private String email;

    @Column(name = "customer_phone_number")
    private String phoneNumber;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_id_card")
    private String idCard;

    @Column(name = "customer_password")
    @JsonIgnore
    private String password;

    @Column(name = "customer_status")
    private Boolean status;

    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "flight_route")
    private String flightRoute;

    @Column(name = "date_of_flight")
    private LocalDateTime dateOfFlight;

    @Column(name = "total_money")
    private Double totalMoney;

//    @ManyToOne
//    @JoinColumn(name = "checkin_status_id")
//    private CheckinStatus checkinStatus;
}
