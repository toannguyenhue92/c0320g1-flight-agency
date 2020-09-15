package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    //Created by: Quân
    Account findByEmailAndStatusIsTrue(String email);
    //Created by: Quân
    boolean existsAccountByEmailAndStatusTrue(String email);

    Account findAccountByEmail(String email);
}
