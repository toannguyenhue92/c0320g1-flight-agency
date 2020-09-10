package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.FlightSchedule;
import vn.codegym.flightagency.repository.FlightScheduleRepository;
import vn.codegym.flightagency.service.FlightScheduleService;

import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    @Autowired
    FlightScheduleRepository flightScheduleRepository;

    //BHung employee tìm kiếm chuyến bay
    @Override
    public List<FlightSchedule> findAllFlightScheduleByEmployee(long deptPlaceId, long arrPlaceId, String deptDate, int capacity, String status) {
        return flightScheduleRepository.findAllFlightScheduleByEmployee(deptPlaceId,arrPlaceId,deptDate,capacity,status);
    }

    //BHung tim kiem chuyen bay
    @Override
    public FlightSchedule findFlightById(Long id) {
        return flightScheduleRepository.findById(id).orElse(null);
    }


}
