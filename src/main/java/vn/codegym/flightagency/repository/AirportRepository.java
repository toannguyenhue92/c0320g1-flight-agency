package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Airport;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
