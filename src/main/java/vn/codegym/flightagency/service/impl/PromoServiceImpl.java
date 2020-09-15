package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.PromoUpdateDTO;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.repository.PromoRepository;
import vn.codegym.flightagency.service.PromoService;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoRepository promoRepository;


    //Tùng
    @Override
    public Promo findById(Long id) {
        return promoRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Promo promo) {
        promoRepository.save(promo);
    }

    @Override
    public void delete(Promo promo) {
        promo.setDelete(false);
        promoRepository.save(promo);
    }


}
