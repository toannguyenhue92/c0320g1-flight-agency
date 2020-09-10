package vn.codegym.flightagency.service;

import vn.codegym.flightagency.model.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {

    //BHung hàm employee tìm chuyến bay
    List<FlightSchedule> findAllFlightScheduleByEmployee(long deptPlaceId, long arrPlaceId, String deptDate,
                                                         int capacity, String status);

    //BHung hàm tìm kiếm chuyến bay theo id
    FlightSchedule findFlightById(Long id);
}
