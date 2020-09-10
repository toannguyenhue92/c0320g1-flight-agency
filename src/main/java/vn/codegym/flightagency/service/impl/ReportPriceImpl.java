package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.ReportPrice;
import vn.codegym.flightagency.dto.ReportPriceDTO;
import vn.codegym.flightagency.repository.ReportPriceRepository;
import vn.codegym.flightagency.service.ReportPriceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReportPriceImpl implements ReportPriceService {
    // Thành
    @Autowired
    private ReportPriceRepository reportPriceRepository;

    @Override
    public List<ReportPrice> findAllTransaction(ReportPriceDTO reportPriceDTO) {
        LocalDateTime date1 = LocalDateTime.of(reportPriceDTO.getDate1(), LocalTime.of(0,0));
        LocalDateTime date2 = LocalDateTime.of(reportPriceDTO.getDate2(), LocalTime.of(23,59));
        return reportPriceRepository.findTotalPricePerDate(date1,date2);
    }
}
