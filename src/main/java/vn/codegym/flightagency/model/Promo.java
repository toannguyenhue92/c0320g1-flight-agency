package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promo")
@Getter
@Setter
@NoArgsConstructor
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_id")
    private Long id;

    @Column(name = "name_promo")
    private String namePromo;

    @Column(name = "discount")
    private Long discount;

//    @OneToMany(mappedBy = "airline_company")
//    @JoinColumn(name = "airline_company")
//    private List<AirlineCompany> airlineCompanyList;

//    @OneToOne
//    @JoinColumn(name = "airline_routes_id")
//    private AirlineRoutes airlineRoutes;

    @Column(name = "date_run_promo_start")
    private LocalDateTime dateRunPromoStart;

    @Column(name = "date_run_promo_end")
    private LocalDateTime dateRunPromoEnd;

    @Column(name = "flight_departure_time_start")
    private LocalDateTime flightDepartureTimeStart;

    @Column(name = "flight_departure_time_end")
    private LocalDateTime flightDepartureTimeEnd;

//    @OneToMany(mappedBy = "customer_ranking")
//    @JoinColumn(name = "customer_ranking")
//    private CustomerRanking customerRanking;

//    @OneToOne
//    @JoinColumn(name = "customer_ranking")
//    private ServiceClass serviceClass;

    @Column(name = "is_delete")
    private boolean isDelete;
    
}
