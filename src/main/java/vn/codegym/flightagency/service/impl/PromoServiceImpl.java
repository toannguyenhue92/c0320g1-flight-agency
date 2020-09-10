package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.repository.PromoRepository;
import vn.codegym.flightagency.service.PromoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PromoServiceImpl implements PromoService {
    LocalDateTime timeNow = LocalDateTime.now();

    @Autowired
    private PromoRepository promoRepository;

    @Override
    public void createNewPromo(Promo promo) {
        promoRepository.save(promo);
    }

    @Override
    public List<Promo> getPromoRunningNow(Pageable pageable) {
        return promoRepository.getPromoRunningNow(this.timeNow, pageable);
    }

    @Override
    public List<Promo> getPromoExpired(Pageable pageable) {
        return promoRepository.getPromoExpired(this.timeNow, pageable);
    }

    @Override
    public List<Promo> getPromoNotActive(Pageable pageable) {
        return promoRepository.getPromoNotActive(this.timeNow, pageable);
    }

    @Override
    public List<Promo> searchPromo(String namePromo, String airline,
                                   String departurePlace, String arrivalPlace,
                                   LocalDateTime promoDateStart, LocalDateTime promoDateEnd,
                                   LocalDateTime flightDepartureDateStart, LocalDateTime flightDepartureDateEnd,
                                   Pageable pageable) {
        return promoRepository.search(namePromo, airline,
                                    departurePlace, arrivalPlace,
                                    promoDateStart, promoDateEnd,
                                    flightDepartureDateStart, flightDepartureDateEnd,
                                    pageable);
    }

    @Override
    public LocalDateTime convertDate(String date) {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(date, DATEFORMATTER);
        LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
        System.out.println(ldt);
        return ldt;
    }
}
