package vn.codegym.flightagency.dto;

import lombok.Data;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;

import java.util.List;

@Data
public class TransactionPassengerDTO {
    private List<TransactionDTO> transactions;
    private List<PassengerDTO> passengers;
}
