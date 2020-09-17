package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Ticket;
import vn.codegym.flightagency.model.TransactionDetail;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.service.TicketService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/admin/tickets")
    public Page<TransactionDetail> ListTicket(@RequestParam(name = "search", defaultValue = "") Optional<String> search,
                                              @PageableDefault(value=2)Pageable pageable){
            return ticketService.findAllByName(search.get(),pageable);
    }

    @GetMapping("/admin/tickets/BookingCode")
    public Page<TransactionDetail> ListTicketByBookingCode(@RequestParam(name = "search", defaultValue = "") Optional<String> search,
                                   @PageableDefault(value=6)Pageable pageable){
        return ticketService.findAllByBookingCode(search.get(),pageable);
    }

    @GetMapping("/admin/tickets/Flight")
    public Page<TransactionDetail> ListTicketByFlight(@RequestParam(name = "search", defaultValue = "") Optional<String> search,
                                                @PageableDefault(value=6)Pageable pageable){
        return ticketService.findAllByFlight(search.get(),pageable);
    }

    @GetMapping("/admin/tickets/{id}")
    public ResponseEntity<TransactionDetail> getTicketById(@PathVariable(value = "id")Long id){
        TransactionDetail ticket = ticketService.findById(id);
        return ResponseEntity.ok().body(ticket);
    }

    @PutMapping("/admin/tickets/{id}")
    public ResponseEntity<Passenger> updateTicketById(@PathVariable(value = "id")Long id, @RequestBody PassengerDTO passengerDTO){
        Passenger ticket = passengerRepository.findById(id).orElse(null);
//        ticket.setFullName(passengerDTO.getFullName);
//        ticket.setEmail(passengerDTO.getEmail);
//            ticketService.save(ticket);
        return ResponseEntity.ok().body(ticket);
    }

    Map<TransactionDetail, LocalDateTime> mapTicketDeleted =new HashMap<>();
    @DeleteMapping("/admin/tickets/{id}")
    public  Map<TransactionDetail,LocalDateTime> deleteTicket(@PathVariable(value = "id")Long id){
        TransactionDetail ticket =ticketService.findById(id);
        LocalDateTime dateTime = LocalDateTime.now();
        mapTicketDeleted.put(ticket,dateTime);
        ticketService.deleteById(id);
        return mapTicketDeleted;
    }
}
