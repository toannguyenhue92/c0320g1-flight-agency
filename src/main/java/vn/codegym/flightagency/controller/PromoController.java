package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.PromoUpdateDTO;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.service.PromoService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PromoController {

    //Tùng

    @Autowired
    public PromoService promoService;


    @GetMapping("employee/promotion/promo-edit/{id}")
    public ResponseEntity<PromoUpdateDTO> updatePromo(@PathVariable Long id, @RequestBody PromoUpdateDTO promoDTO){
        Promo promo = promoService.findById(id);
        if(promo == null){
            return new ResponseEntity<PromoUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        promoService.save(promoDTO);
        return new ResponseEntity<PromoUpdateDTO>(promoDTO, HttpStatus.OK);
    }

    @GetMapping("employee/promotion/promo-delete/{id}")
    public Map<String, Boolean> deletePromo(@PathVariable(value = "id") Long promoId){
        Promo promo = promoService.findById(promoId);
        promoService.delete(promo);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
