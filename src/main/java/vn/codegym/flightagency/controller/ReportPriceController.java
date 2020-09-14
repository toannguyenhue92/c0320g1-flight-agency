package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.ReportPrice;
import vn.codegym.flightagency.dto.ReportPriceDTO;
import vn.codegym.flightagency.service.ReportPriceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportPriceController {
    // Thành
    @Autowired
    private ReportPriceService transactionService;

    @PostMapping("/pricereport")
    public List<ReportPrice> getAllTransactions(@RequestBody ReportPriceDTO reportPriceDTO){
        List<ReportPrice> reportPrice = transactionService.findAllTransaction(reportPriceDTO);
        return transactionService.findAllTransaction(reportPriceDTO);
    }

}
