package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.PromoUpdateDTO;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.Branch;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.repository.AirportRepository;
import vn.codegym.flightagency.repository.BranchRepository;
import vn.codegym.flightagency.service.PromoService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PromoController {

    //Tùng

    @Autowired
    public PromoService promoService;

    @Autowired
    public BranchRepository branchRepository;

    @Autowired
    public AirportRepository airportRepository;


    @GetMapping("/employee/promotion/promo-edit/{id}")
    public ResponseEntity<Promo> getPromo(@PathVariable Long id) {
        Promo promo = promoService.findById(id);
        if (promo == null) {
            return new ResponseEntity<Promo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Promo>(promo, HttpStatus.OK);
    }


    @PutMapping("employee/promotion/update-edit/{id}")
    public ResponseEntity<Promo> updatePromo(@PathVariable Long id, @RequestBody PromoUpdateDTO promoUpdateDTO) {
        Promo promo = promoService.findById(id);
        promo.setNamePromo(promoUpdateDTO.getNamePromo());
        promo.setDiscount(promoUpdateDTO.getDiscount());
        promo.setAirline(promoUpdateDTO.getAirline());
        promo.setDeparturePlace(promoUpdateDTO.getDeparturePlace());
        promo.setArrivalPlace(promoUpdateDTO.getArrivalPlace());
        promo.setPromoDateStart(promoUpdateDTO.getPromoDateStart());
        promo.setPromoDateEnd(promoUpdateDTO.getPromoDateEnd());
        promo.setFlightDepartureDateStart(promoUpdateDTO.getFlightDepartureDateStart());
        promo.setFlightDepartureDateEnd(promoUpdateDTO.getFlightDepartureDateEnd());
        promoService.save(promo);
        return ResponseEntity.ok().body(promo);
    }

    @PostMapping("employee/promotion/promo-delete/{id}")
    public Map<String, Boolean> deletePromo(@PathVariable(value = "id") Long promoId) {
        Promo promo = promoService.findById(promoId);
        promo.setDelete(Boolean.TRUE);
        promoService.save(promo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("employee/promotion/airline")
    public ResponseEntity<List<Branch>> getApiOfAirline() {
        List<Branch> airLineList = branchRepository.findAll();
        return new ResponseEntity<List<Branch>>(airLineList, HttpStatus.OK);
    }

    @GetMapping("employee/promotion/airport")
    public ResponseEntity<List<Airport>> getApiOfAirport() {
        List<Airport> airportList = airportRepository.findAll();
        return new ResponseEntity<List<Airport>>(airportList, HttpStatus.OK);
    }
}