package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.EmployeePassengerDTO;
import vn.codegym.flightagency.dto.EmployeeTransactionDTO;
import vn.codegym.flightagency.model.Transaction;

import java.util.List;

public interface EmployeeService {

    //BHung luu trans và pass
    List<Transaction> saveTransactionsAndTickets(List<EmployeeTransactionDTO> transactions, List<EmployeePassengerDTO> passengers);

    //BHung find trans By Id
    Transaction findTransactionById(Long id);
}
