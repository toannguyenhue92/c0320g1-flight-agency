package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
