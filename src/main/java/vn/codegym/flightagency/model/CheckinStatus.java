package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "checkin_statuses")
@Getter
@Setter
@NoArgsConstructor
public class CheckinStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkin_status_id")
    private Long id;

    @Column(name = "checkin_status_name")
    private String name;
}