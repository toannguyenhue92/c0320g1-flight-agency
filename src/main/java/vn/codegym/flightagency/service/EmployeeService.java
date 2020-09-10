package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.PassengerDTO;
import vn.codegym.flightagency.dto.TransactionDTO;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;

import java.util.List;

public interface EmployeeService {

    //BHung luu trans và pass
    void saveTransactionsAndTickets(List<TransactionDTO> transactions, List<PassengerDTO> passengers);

    //BHung find trans By Id
    Transaction findTransactionById(Long id);
}
