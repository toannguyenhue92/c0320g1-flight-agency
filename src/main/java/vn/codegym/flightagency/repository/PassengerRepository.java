package vn.codegym.flightagency.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>, JpaSpecificationExecutor<Passenger> {

    // Thành Long
    Page<Passenger> findAllByCheckinIsTrue(Pageable pageable);

    // Creator: Duy
    Passenger findPassengerByIdentifierCard(String idCard);

}
