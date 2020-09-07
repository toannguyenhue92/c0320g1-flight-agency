package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmailAndStatusIsTrue(String email);

    boolean existsAccountByEmailAndStatusTrue(String email);
}
