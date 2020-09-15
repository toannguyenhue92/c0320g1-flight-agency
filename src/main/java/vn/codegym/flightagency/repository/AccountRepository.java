package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    //Created by: Quân
    Account findByEmailAndStatusIsTrue(String email);
    //Created by: Quân
    boolean existsAccountByEmailAndStatusTrue(String email);
    //BHung
    Account findByEmail(String email);
}
