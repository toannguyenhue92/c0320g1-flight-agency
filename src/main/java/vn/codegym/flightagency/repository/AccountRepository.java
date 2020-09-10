package vn.codegym.flightagency.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> , JpaSpecificationExecutor<Account> {
    //creator: Mậu
//    @Query(value = "select * from accounts where status = false AND role = 'ROLE_CUSTOMER'", nativeQuery = true)
//    Page<Account> findAllByStatusIsFalse(Long id);

}
