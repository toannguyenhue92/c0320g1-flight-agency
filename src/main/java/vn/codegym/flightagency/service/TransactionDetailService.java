package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.dto.CheckinDto;
import vn.codegym.flightagency.model.TransactionDetail;

public interface TransactionDetailService {
    void saveTransactionDetail(BookingDTO booking);

    // Thành Long
    Page<CheckinDto> findTransactionDetailByCriteria(Specification<TransactionDetail> spec, int page);

    // Thành Long
    Specification<TransactionDetail> getFilter(String bookingCode, String fullName);
}
