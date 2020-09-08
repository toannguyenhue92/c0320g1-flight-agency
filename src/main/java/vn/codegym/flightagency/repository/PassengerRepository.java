package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Page<Passenger> findAllByCheckinIsTrue(Pageable pageable);
}
