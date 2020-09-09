package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.repository.FlightScheduleRepository;
import vn.codegym.flightagency.service.FlightScheduleService;

@Service
public class FlightScheduleImpl implements FlightScheduleService {
    //D-Bach
    @Autowired
    FlightScheduleRepository flightScheduleRepository;

    //D-Bach
    @Override
    public Page<FlightSchedule> findAllFlightSchedule(Pageable pageable) {
        return flightScheduleRepository.findAll(pageable);
    }
}
