package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.service.BillService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {
    @Autowired
    private BillService billService;

    //son
    //Lay danh sach bills
    @GetMapping(path="bills-list")
    public ResponseEntity<Page<Bill>> getBillsListForEmployee(Pageable pageable){
        Page<Bill> billList = billService.findAllBills(pageable);
        if(billList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(billList,HttpStatus.OK);
        }
    };
    @GetMapping(path="bill-search")
    public ResponseEntity<Page<Bill>> searchBillList(@RequestParam(name="billCode",required = false,defaultValue = "") String billCode,
                                                     @RequestParam(name="billTax",required = false,defaultValue = "") String billTax,
                                                     @RequestParam(name="name",required = false,defaultValue = "") String name,
                                                     Pageable pageable) {
        String noParam = "";
        System.out.println(billCode);
        System.out.println(billTax);
        System.out.println(name);
        Page<Bill> resultBill = billService.searchBills(billCode, billTax, name, pageable);
        if(resultBill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(resultBill,HttpStatus.OK);
        }
    }
}
