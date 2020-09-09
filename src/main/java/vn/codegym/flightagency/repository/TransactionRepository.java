package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Created by Toàn
    @Query(value = "SELECT t FROM Transaction t WHERE t.account.id = ?1 AND t.status = 'Chờ thanh toán'")
    List<Transaction> findUnpaidByAccountId(Long accountId);

    // Created by Toàn
    @Query(value = "SELECT t FROM Transaction t " +
            "WHERE t.id = ?1 AND t.account.phoneNumber = ?2 AND t.status = 'Chờ thanh toán'")
    Optional<Transaction> findByIdAndPhone(Long id, String phone);
}
