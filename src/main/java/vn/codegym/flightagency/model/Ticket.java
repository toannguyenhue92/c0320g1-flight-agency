package vn.codegym.flightagency.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "airline")
    private String airline;

    @Column(name = "type_ticket")
    private String typeTicket;

    @Column(name = "chair")
    private String chair;

    @Column(name = "price")
    private Double price;

    @Column(name = "taxes_and_fees")
    private Double taxesAndFees;

    @Column(name = "type_customer")
    private String typeCustomer;

    @Column(name = "name_customer")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "extra_luggage")
    private String extraLuggage;

}
