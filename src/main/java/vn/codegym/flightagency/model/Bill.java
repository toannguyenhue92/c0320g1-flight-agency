package vn.codegym.flightagency.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "bills")
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="date_created")
    private LocalDateTime dateCreated;

    @JoinColumn(name="transaction_id")
    @OneToOne
    private Transaction transaction;

    @Column(name="bill_code")
    private String billCode;

    @Column(name="tax_code")
    private String taxCode;


}

