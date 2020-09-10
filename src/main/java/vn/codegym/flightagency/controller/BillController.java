package vn.codegym.flightagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.SelectDto;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.service.BillService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class BillController {

    @Autowired
    BillService billService;
    // C-Ngan
    @GetMapping("customer/bills/{accountId}")
    public ResponseEntity<Page<Bill>> getBillsByAccountId(@PathVariable Long accountId,
                                                          @RequestParam( name = "page", defaultValue = "0", required = false) int page){
        Page<Bill> bills = billService.getBillsByAccountId(accountId,page);
        if(bills.isEmpty()) return new ResponseEntity<>(bills, HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(bills);


 }
    // C-Ngan
    @GetMapping("customer/bills/search/{accountId}")
    public ResponseEntity<Page<Bill>> getSearchedBillsByAccountId(@PathVariable Long accountId,
                                                                  @RequestParam( name = "page", defaultValue = "0", required = false) int page,
                                                                  @RequestParam( name = "billCode", defaultValue = "", required = false) String billCode,
                                                                  @RequestParam( name = "brand", defaultValue = "", required = false) String brand,
                                                                  @RequestParam( name = "departure", defaultValue = "", required = false) String departure,
                                                                  @RequestParam( name = "arrival", defaultValue = "", required = false) String arrival) {
        Page<Bill> bills = billService.getSearchedBills(accountId, billCode, brand, departure, arrival, page);
        if (bills.isEmpty()) return new ResponseEntity<>(bills, HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(bills);
    }
    // C-Ngan
    @GetMapping("customer/bills/select-info")
    public ResponseEntity<SelectDto> getSelects(){
        return ResponseEntity.ok(this.billService.getSelectDto());
    }



}
