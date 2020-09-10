package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Account;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.PassengerService;
import vn.codegym.flightagency.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PassengerService passengerService;

    // Creator: Duy
    @Override
    public void addBooking(BookingDTO bookingDTO) {
        // transfer to passenger
        // add passenger
        List<Passenger> passengerList = addAllPassengers(bookingDTO.getDepPassengers());
        Transaction depTransaction = createTransaction(bookingDTO.getDepFlightId(), passengerList, bookingDTO.getAccountId(), bookingDTO.getDepTotalPrice());
        transactionRepository.save(depTransaction);

        // if round-trip
        if (bookingDTO.getRetFlightId() != 0) {
            passengerList = addAllPassengers(bookingDTO.getRetPassengers());
            Transaction retTransaction = createTransaction(bookingDTO.getRetFlightId(), passengerList, bookingDTO.getAccountId(), bookingDTO.getRetTotalPrice());
            transactionRepository.save(retTransaction);
        }
    }

    // Creator: Duy
    // create transaction
    private Transaction createTransaction(Long flightScheduleId, List<Passenger> passengers, Long accountId, Double totalPrice) {
        Transaction transaction = new Transaction();
        FlightSchedule flightSchedule = new FlightSchedule();
        LocalDateTime createdDateTime = LocalDateTime.now();
        LocalDateTime dueDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(createdDateTime.getHour() + 2, createdDateTime.getMinute()));
        Account account = new Account();
        account.setId(accountId);
        flightSchedule.setId(flightScheduleId);
        transaction.setFlightSchedule(flightSchedule);
        transaction.setAccount(account);
        transaction.setPrice(totalPrice);
        transaction.setPassengers(passengers);
        transaction.setStatus("Chờ thanh toán");
        transaction.setCreatedTime(createdDateTime);
        transaction.setDueTime(dueDateTime);
        return transaction;
    }

    // Creator: Duy
    // save passenger
    private List<Passenger> addAllPassengers(List<PassengerInfoDTO> passengerInfoDtoList) {
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < passengerInfoDtoList.size(); i++) {
            Passenger passenger = passengerService.saveAndUpdate(passengerInfoDtoList.get(i));
            passengerList.add(passenger);
        }
        return  passengerList;
    }
}
