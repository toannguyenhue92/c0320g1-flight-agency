package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.repository.PromoRepository;
import vn.codegym.flightagency.service.PromoService;

@Service
public class PromoServiceImpl extends PromoService {

    @Autowired
    private PromoRepository promoRepository;

}
