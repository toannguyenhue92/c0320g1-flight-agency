package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>, JpaSpecificationExecutor<TransactionDetail> {
}
