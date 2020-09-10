package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.repository.BillRepository;
import vn.codegym.flightagency.service.BillService;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Override
    public Page<Bill> findAllBills(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Page<Bill> findBillByBillsCode(Pageable pageable, String billCode) {
        return billRepository.findBillsByBillCode(pageable,billCode);
    }

    @Override
    public Page<Bill> searchBills(String billCode, String billTax, String name, Pageable pageable) {
        return billRepository.findByBillCodeIsOrTaxCodeIsOrTransaction_Account_FullName(billCode,billTax,name,pageable);
    }
}
