package vn.codegym.flightagency.service;

import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Promo;

import java.time.LocalDateTime;
import java.util.List;

public interface PromoService {

    public void createNewPromo(Promo promo);

    public List<Promo> getPromoRunningNow(Pageable pageable);

    public List<Promo> getPromoExpired(Pageable pageable);

    public List<Promo> getPromoNotActive(Pageable pageable);

    public List<Promo> searchPromo(String namePromo, String airline,
                                   String departurePlace, String arrivalPlace,
                                   LocalDateTime promoDateStart, LocalDateTime promoDateEnd,
                                   LocalDateTime flightDepartureDateStart, LocalDateTime flightDepartureDateEnd,
                                   Pageable pageable);

    public LocalDateTime convertDate(String date);
}

