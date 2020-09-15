package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long > {

    // Creator: Duy
    Passenger findPassengerByIdentifierCard(String idCard);

    //BHung
    Passenger findByIdentifierCard(String identifyCard);
    @Query(value = "select * from passengers", nativeQuery = true)
    Page<Passenger> findAllCustomer(Pageable pageable);
}
