package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Account;

import java.time.LocalDate;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    //Created by: Quân
    Account findByEmailAndStatusIsTrue(String email);

    //Created by: Quân
    boolean existsAccountByEmailAndStatusTrue(String email);

    Account findAccountByEmail(String email);

//    //    CREATE BY ANH DUC
//    public Page<Account> findAllAccount(Pageable pageable);
//
//    ;

    //    CREATE BY ANH DUC
    public Account findByEmail(String email);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByFullName(String name, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByEmail(String email, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByBirthDate(LocalDate birthday, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByPhoneNumber(String phone, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByAddress(String gender, Pageable pageable);

    //    CREATE BY ANH DUC
    public Page<Account> findAllByRole(String role, Pageable pageable);
     Page<Account> findAllByGender(String gender, Pageable pageable);
}
