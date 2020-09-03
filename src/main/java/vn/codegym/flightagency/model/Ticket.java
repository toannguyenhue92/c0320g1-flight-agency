package vn.codegym.flightagency.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long id;

    @Column(name = "depature")
    private String depature;

    @Column(name = "destination")
    private String destination;

    @Column(name = "depature_time")
    private Date depatureTtime;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "airline")
    private String airline;

    @Column(name = "type_ticket")
    private String typeTicket;

    @Column(name = "chair")
    private String chair;

    @Column(name = "price")
    private int price;

    @Column(name = "taxes_and_fees")
    private int taxesAndFees;

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
