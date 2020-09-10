package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.dto.FlightSearchDTO;
import vn.codegym.flightagency.model.FlightSchedule;

import java.time.LocalDate;
import java.util.List;

public interface FlightScheduleService {
    //D-Bach
    Page<FlightSchedule> findAllFlightSchedule(Pageable pageable);

    //A-Duy
    List<FlightSchedule> searchFlights(FlightSearchDTO flights);
}
