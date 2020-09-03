package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
