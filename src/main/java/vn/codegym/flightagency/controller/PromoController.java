package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.Branch;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.repository.AirportRepository;
import vn.codegym.flightagency.repository.BranchRepository;
import vn.codegym.flightagency.repository.PromoRepository;
import vn.codegym.flightagency.service.PromoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/employee/promotion")
public class PromoController {

    public int NUMBER_OF_PAGE = 3;

    @Autowired
    private PromoService promoService;
    @Autowired
    private PromoRepository promoRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping(value="")
    public ResponseEntity<List<Promo>> findAllAndSearchPromo(@RequestParam("status") String status,
                                                             @RequestParam("page") int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1, this.NUMBER_OF_PAGE, Sort.by("id"));
        List<Promo> promotions = new ArrayList<>();
        switch (status) {
            case "active":
                promotions = promoService.getPromoRunningNow(pageable);
                break;
            case "not-active":
                promotions = promoService.getPromoNotActive(pageable);
                break;
            case "expired":
                promotions = promoService.getPromoExpired(pageable);
                break;
        }
        if (promotions.isEmpty()) {
            return new ResponseEntity<List<Promo>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Promo>>(promotions, HttpStatus.OK);
    }

    @GetMapping(value="/airline")
    public ResponseEntity<List<Branch>> getApiOfAirline() {
        List<Branch> airLineList = branchRepository.findAll();
        return new ResponseEntity<List<Branch>>(airLineList, HttpStatus.OK);
    }

    @GetMapping(value="/airport")
    public ResponseEntity<List<Airport>> getApiOfAirport() {
        List<Airport> airportList = airportRepository.findAll();
        return new ResponseEntity<List<Airport>>(airportList, HttpStatus.OK);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Promo> createNewPromo(@RequestBody Promo promo) {
        promo.setDelete(Boolean.FALSE);
        promoService.createNewPromo(promo);
//        return ResponseEntity.ok().body(promo);
        return new ResponseEntity<Promo>(promo, HttpStatus.CREATED);
    }

    @PostMapping(value="/search")
    public ResponseEntity<List<Promo>> searchPromo(@RequestParam("page") int currentPage,
                                                   @RequestBody Map<String, Object> infoSearch) {
        Pageable pageable = PageRequest.of(currentPage-1, this.NUMBER_OF_PAGE, Sort.by("id"));

        String namePromo = infoSearch.get("namePromo").toString();
        String airline = infoSearch.get("airline").toString();
        String departurePlace = infoSearch.get("departurePlace").toString();
        String arrivalPlace = infoSearch.get("arrivalPlace").toString();
        LocalDateTime promoDateStart = this.promoService.convertDate(infoSearch.get("promoDateStart").toString());
        LocalDateTime promoDateEnd = this.promoService.convertDate(infoSearch.get("promoDateEnd").toString());
        LocalDateTime flightDepartureDateStart = this.promoService.convertDate(infoSearch.get("flightDepartureDateStart").toString());
        LocalDateTime flightDepartureDateEnd = this.promoService.convertDate(infoSearch.get("flightDepartureDateEnd").toString());

        List<Promo> promotions = promoService.searchPromo(namePromo, airline, departurePlace, arrivalPlace,
                promoDateStart, promoDateEnd,
                flightDepartureDateStart, flightDepartureDateEnd,
                pageable);
        return new ResponseEntity<List<Promo>>(promotions, HttpStatus.OK);
    }

}
