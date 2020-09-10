package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Page<Bill> findBillsByBillCode(Pageable pageable, String billCode);
    Page<Bill> findByBillCodeIsAndTaxCodeIsAndTransaction_Account_FullName(String billCode, String billTax, String name, Pageable pageable);
    Page<Bill> findByBillCodeIsOrTaxCodeIsOrTransaction_Account_FullName(String billCode, String billTax, String name, Pageable pageable);

}
