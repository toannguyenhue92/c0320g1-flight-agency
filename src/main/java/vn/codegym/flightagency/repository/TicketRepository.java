package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Ticket;
import vn.codegym.flightagency.model.TransactionDetail;

@Repository
public interface TicketRepository extends JpaRepository<TransactionDetail,Long> {
    Page<TransactionDetail> findAllByPassenger_FullNameContaining(String search, Pageable pageable);
    Page<TransactionDetail> findAllByTransaction_FlightSchedule_FlightCodeContaining(String search, Pageable pageable);
    Page<TransactionDetail> findAllByTransaction_FlightSchedule_ArrivalAirport_CityContaining(String transaction_flightSchedule_arrivalAirport_name, Pageable pageable);
}
