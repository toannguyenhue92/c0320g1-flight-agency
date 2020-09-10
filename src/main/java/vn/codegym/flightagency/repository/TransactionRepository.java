package vn.codegym.flightagency.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
