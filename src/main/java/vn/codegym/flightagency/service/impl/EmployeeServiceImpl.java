package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.dto.TransactionDTO;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.repository.FlightScheduleRepository;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    FlightScheduleRepository flightScheduleRepository;

    //BHung luu ve
    @Override
    public void saveTransactionsAndTickets(List<TransactionDTO> transactions, List<PassengerDTO> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        for(PassengerDTO passengerDTO: passengers){
            //Kiểm tra coi idCard đã tồn tại
            Passenger passengerSearch = passengerRepository.findByIdentifierCard(passengerDTO.getIdentifierCard());
            if(passengerSearch==null){
                Passenger passenger = new Passenger();
                passenger.setEmail(passengerDTO.getEmail());
                passenger.setFullName(passengerDTO.getFullName());
                passenger.setGender(passengerDTO.getGender());
                passenger.setIdentifierCard(passengerDTO.getIdentifierCard());
                passenger.setPhoneNumber(passengerDTO.getPhoneNumber());
                passenger.setCheckin(passengerDTO.getCheckin());
                passengerRepository.save(passenger);
                passengerList.add(passenger);
            }else {
                passengerList.add(passengerSearch);
            }
        }
        for(TransactionDTO transactionDTO: transactions) {
            Transaction transaction = new Transaction();
            transaction.setAccount(transactionDTO.getAccount());
            transaction.setPassengers(passengerList);
            transaction.setCreatedTime(transactionDTO.getCreatedTime());
            transaction.setDueTime(transactionDTO.getDueTime());
            transaction.setFlightSchedule(transactionDTO.getFlightSchedule());
            transaction.setPrice(transactionDTO.getPrice());
            transaction.setStatus(transactionDTO.getStatus());
            transactionRepository.save(transaction);
            //update lai capacity flight
            FlightSchedule flightSchedule = flightScheduleRepository.findById(transactionDTO.getFlightSchedule().getId()).orElse(null);
            if(flightSchedule!=null){
                flightSchedule.setFlightCapacity(flightSchedule.getFlightCapacity()-passengers.size());
                if(flightSchedule.getFlightCapacity()==0){
                    flightSchedule.setStatus("inactive");
                }
                flightScheduleRepository.save(flightSchedule);
            }
        }
    }

    //BHung
    @Override
    public Transaction findTransactionById(Long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }
}
