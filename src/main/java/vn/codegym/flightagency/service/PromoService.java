package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.PromoUpdateDTO;
import vn.codegym.flightagency.model.Promo;

public interface PromoService {

    //Tùng
    Promo findById(Long id);

    void save(PromoUpdateDTO promoDTO);

    void delete(Promo promo);
}
