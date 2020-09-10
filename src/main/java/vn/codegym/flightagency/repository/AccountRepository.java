package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByEmail(String username);

}
