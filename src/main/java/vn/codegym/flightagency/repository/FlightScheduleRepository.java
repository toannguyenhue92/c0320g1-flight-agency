package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.FlightSchedule;

import java.util.List;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule,Long> {

    //BHung hàm tìm kiếm chuyến bay Employee
    @Query(value = "select * from flight_schedules where departure_airport_id = ?1 and arrival_airport_id = ?2 and departure_time like %?3% and flight_capacity >=?4 and status like %?5%",nativeQuery = true)
    List<FlightSchedule> findAllFlightScheduleByEmployee(long departPlaceId, long arrPlaceId, String departDate, int capacity, String status);
}
