package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Created by Toàn
    @Query(value = "SELECT t FROM Transaction t WHERE t.account.id = ?1 AND t.status = 'chờ thanh toán'")
    List<Transaction> findUnpaidByAccountId(Long accountId);

    // Created by Toàn
    @Query(value = "SELECT t FROM Transaction t WHERE t.dueTime <= ?1 AND t.status = 'chờ thanh toán'")
    List<Transaction> findOverdueTransaction(LocalDateTime dueTime);

}
