package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Transaction;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // C-Ngan
    Page<Transaction> findByAccount_IdIs(Long accountId, Pageable pageable);

    // C-Ngan
    Page<Transaction> findByAccount_IdIsAndStatusIs(Long accountId, String status, Pageable pageable);

    // C-Ngan


}
