package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Ticket;
import vn.codegym.flightagency.model.TransactionDetail;

public interface TicketService {
        Page<TransactionDetail> findAllByName(String search, Pageable pageable);
//        Page<Ticket> findAllByName(String search, Pageable pageable);
        Page<TransactionDetail> findAllByFlight(String search, Pageable pageable);
//        Page<Ticket> findAllByFlight(String search, Pageable pageable);
        Page<TransactionDetail> findAllByBookingCode(String search, Pageable pageable);
//        Page<Ticket> findAllByBookingCode(String search, Pageable pageable);
        void save(Passenger ticket);
        void deleteById(Long id);
        TransactionDetail findById(Long id);
//        void save(Ticket ticket);
//        void deleteById(Long id);
//        Ticket findById(Long id);

}
