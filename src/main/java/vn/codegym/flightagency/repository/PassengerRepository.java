package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long>, JpaSpecificationExecutor<Passenger> {

}
