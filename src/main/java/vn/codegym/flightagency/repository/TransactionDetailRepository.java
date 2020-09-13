package vn.codegym.flightagency.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.TransactionDetail;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    // Thành Long
    List<TransactionDetail> findAllByTransactionIdAndPassenger_CheckinIsFalse(Long id);
}
