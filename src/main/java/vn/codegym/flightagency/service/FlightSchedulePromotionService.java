package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.model.FlightSchedule;

import java.util.List;

public interface FlightSchedulePromotionService {
    List<FlightSchedule> searchFlightsPromotion(FlightSearchDTO flights);
}
