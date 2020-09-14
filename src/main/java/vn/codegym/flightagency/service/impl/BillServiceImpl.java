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
        if(billCode.equals("") && billTax.equals("")){
            return billRepository.findByTransaction_Account_FullNameContaining(name,pageable);
        } else if (billCode.equals("") && name.equals("")){
            return billRepository.findByTaxCodeIs(billTax,pageable);
        } else if (billTax.equals("") && name.equals("")) {
            return billRepository.findByBillCodeIs(billCode,pageable);
        } else if (!billCode.equals("") && !billTax.equals("")){
            return  billRepository.findByBillCodeIsAndTaxCodeIsOrTransaction_Account_FullNameContaining(billCode,billTax,name,pageable);
        } else if (!billCode.equals("")) {
            return billRepository.findByBillCodeIsAndTransaction_Account_FullNameContaining(billCode,name,pageable);
        }
        return billRepository.findByBillCodeIsOrTaxCodeIsOrTransaction_Account_FullNameContaining(billCode,billTax,name,pageable);
    }
}
