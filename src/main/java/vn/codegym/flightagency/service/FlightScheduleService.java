package vn.codegym.flightagency.service;

import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.model.FlightSchedule;

import java.time.LocalDate;
import java.util.List;

public interface FlightScheduleService {

    List<FlightSchedule> searchFlights(FlightSearchDTO flights);
}
