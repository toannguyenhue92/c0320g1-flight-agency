package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.dto.SelectDto;
import vn.codegym.flightagency.model.Bill;


public interface BillService {
    // C-Ngan
    Page<Bill> getBillsByAccountId(Long accountId, int page);
    Page<Bill> getSearchedBills(Long accountId, String billCode, String brand, String departure, String arrival, int page);
    SelectDto getSelectDto();


}
