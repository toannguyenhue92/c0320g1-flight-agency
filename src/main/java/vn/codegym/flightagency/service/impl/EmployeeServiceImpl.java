package vn.codegym.flightagency.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.EmployeePassengerDTO;
import vn.codegym.flightagency.dto.EmployeeTransactionDTO;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.model.TransactionDetail;
import vn.codegym.flightagency.repository.FlightScheduleRepository;
import vn.codegym.flightagency.repository.PassengerRepository;
import vn.codegym.flightagency.repository.TransactionDetailRepository;
import vn.codegym.flightagency.repository.TransactionRepository;
import vn.codegym.flightagency.service.EmployeeService;
import vn.codegym.flightagency.service.TransactionService;

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
    @Autowired
    TransactionDetailRepository transactionDetailRepository;
    @Autowired
    TransactionService transactionService;

    //BHung luu ve
    @Override
    public List<Transaction> saveTransactionsAndTickets(List<EmployeeTransactionDTO> transactions, List<EmployeePassengerDTO> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        for (EmployeePassengerDTO passengerDTO : passengers) {
            ModelMapper modelMapper = new ModelMapper();
            Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
            //Kiểm tra coi idCard đã tồn tại
            if (passengerDTO.getIdentifierCard() != null) {
                Passenger passengerSearch = passengerRepository.findByIdentifierCard(passengerDTO.getIdentifierCard());
                if (passengerSearch != null) {
                    passenger.setId(passengerSearch.getId());
                }
            }
            passengerRepository.save(passenger);
            passengerList.add(passenger);
        }
        for (int k = 0; k < transactions.size(); k++) {
            Transaction transaction = new Transaction();
            transaction.setAccount(transactions.get(k).getAccount());
            transaction.setCreatedTime(transactions.get(k).getCreatedTime());
            transaction.setDueTime(transactions.get(k).getDueTime());
            transaction.setFlightSchedule(transactions.
                    get(k).getFlightSchedule());
            transaction.setPrice(transactions.get(k).getPrice());
            transaction.setStatus(transactions.get(k).getStatus());
            Transaction newTransaction = transactionService.save(transaction);
            transactionList.add(newTransaction);
            //update vào transaction_detail
            for (int i = 0; i < passengerList.size(); i++) {
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setTransaction(transaction);
                transactionDetail.setPassenger(passengerList.get(i));
                if (k == 0) {
                    transactionDetail.setBaggage(getLuggageWeight(passengers.get(i).getDeptLuggagePrice()));
                } else {
                    transactionDetail.setBaggage(getLuggageWeight(passengers.get(i).getArvLuggagePrice()));
                }
                transactionDetailRepository.save(transactionDetail);
            }

            //update lai capacity flight
            FlightSchedule flightSchedule = flightScheduleRepository.findById(transactions.get(k).getFlightSchedule().getId()).orElse(null);
            if (flightSchedule != null) {
                flightSchedule.setFlightCapacity(flightSchedule.getFlightCapacity() - passengers.size());
                if (flightSchedule.getFlightCapacity() == 0) {
                    flightSchedule.setStatus("inactive");
                }
                flightScheduleRepository.save(flightSchedule);
            }
        }
        return transactionList;
    }


    //BHung
    @Override
    public Transaction findTransactionById(Long id) {
        return this.transactionRepository.findById(id).orElse(null);
    }

    //BHung lấy kg hành lý
    private byte getLuggageWeight(int money) {
        switch (money) {
            case 170000:
                return 15;
            case 225000:
                return 20;
            case 275000:
                return 25;
            default:
                return 7;
        }
    }
}
