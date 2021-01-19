package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Ticket;
import vn.codegym.flightagency.model.TransactionDetail;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.repository.TicketRepository;
import vn.codegym.flightagency.service.TicketService;

import javax.transaction.Transactional;


@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Page<TransactionDetail> findAllByName(String search, Pageable pageable) {
        return ticketRepository.findAllByPassenger_FullNameContaining(search,pageable);
    }

    @Override
    public Page<TransactionDetail> findAllByFlight(String search, Pageable pageable) {
        return ticketRepository.findAllByTransaction_FlightSchedule_ArrivalAirport_CityContaining(search,pageable);
    }

    @Override
    public Page<TransactionDetail> findAllByBookingCode(String search, Pageable pageable) {
        return ticketRepository.findAllByTransaction_FlightSchedule_FlightCodeContaining(search,pageable);
    }

    @Override
    public void save(Passenger ticket) { passengerRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TransactionDetail findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}
