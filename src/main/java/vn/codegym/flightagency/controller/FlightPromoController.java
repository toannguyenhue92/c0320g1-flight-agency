package vn.codegym.flightagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.model.Airport;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.model.Promo;
import vn.codegym.flightagency.service.AirportService;
import vn.codegym.flightagency.service.PromoService;
import vn.codegym.flightagency.service.impl.FlightSchedulePromotionServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class FlightPromoController {

    @Autowired
    private FlightSchedulePromotionServiceImpl flightSchedulePromotionService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private PromoService promoService;

    // Creator: An
    // Find flight schedule promo
//    @PostMapping("/flight-schedule-promo")
//    public ResponseEntity<List<FlightSchedule>> search(@RequestBody FlightSearchDTO flights)
//            throws ViolatedException {
//        List<FlightSchedule> flightSchedules = flightSchedulePromotionService.searchFlightsPromotion(flights);
//        return ResponseEntity.ok(flightSchedules);
//    }

    // Creator: An
    // Get all airport
    @GetMapping("/airport-promo")
    public ResponseEntity<List<Airport>> getAirports() {
        List<Airport> airports = airportService.getAirports();
        return ResponseEntity.ok(airports);
    }

    @PostMapping("/flight-promotion")
    public ResponseEntity<List<FlightSchedule>> ListSchedulePromotion(@RequestBody FlightSearchDTO flights){
        List<FlightSchedule>  flightSchedules = flightSchedulePromotionService.searchFlightsPromotion(flights);
        return ResponseEntity.ok(flightSchedules);
    }

    @GetMapping("/promotion")
    public ResponseEntity<List<Promo>> ListPromotion(){
        List<Promo>  flightSchedulesPromo = promoService.getPromo();
        return ResponseEntity.ok(flightSchedulesPromo);
    }


}
