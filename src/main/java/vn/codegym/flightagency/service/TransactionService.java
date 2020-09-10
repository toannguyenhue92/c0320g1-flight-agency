package vn.codegym.flightagency.service;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.model.Transaction;

public interface TransactionService {

    // Creator: Duy
    Transaction save(Transaction transaction);

    //Creator: Duy
    Transaction createTransaction(Long flightScheduleId, Long accountId, Double totalPrice);

}
