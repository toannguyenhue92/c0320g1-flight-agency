package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Page<Account> findAllByStatusFalseAndRoleContains(Pageable pageable,String fullName);
}
