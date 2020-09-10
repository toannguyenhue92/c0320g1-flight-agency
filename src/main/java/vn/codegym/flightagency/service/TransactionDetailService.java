package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.BookingDTO;

public interface TransactionDetailService {
    void saveTransactionDetail(BookingDTO booking);
}
