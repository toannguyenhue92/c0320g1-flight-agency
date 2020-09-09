package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.FlightSchedule;

public interface FlightScheduleService {
    //D-Bach
    Page<FlightSchedule> findAllFlightSchedule (Pageable pageable);
}
