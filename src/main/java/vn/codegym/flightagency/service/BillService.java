package vn.codegym.flightagency.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Bill;

public interface BillService {
    //Son
    Page<Bill> findAllBills (Pageable pageable);
    Page<Bill> findBillByBillsCode(Pageable pageable, String billCode);
    Page<Bill> searchBills(String billCode, String taxCode, String name, Pageable pageable);
}
