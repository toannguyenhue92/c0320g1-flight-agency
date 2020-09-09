package vn.codegym.flightagency.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.FlightSchedule;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

//    @Query(nativeQuery = true, value =
//            "Select * from flight_schedules where " +
//            "departure_airport_id = ?1 and " +
//            "arrival_airport_id = ?2  and " +
//            "status = 'active' and " +
//            "departure_time between ?3 and ?4 "
//    )
    @Query(value =
            "Select f from FlightSchedule f where " +
            "f.departureAirport.id = ?1 and " +
            "f.arrivalAirport.id = ?2  and " +
            "f.status = 'active' and " +
            "f.departureDateTime >= ?3 and " +
            "f.departureDateTime <= ?4"
    )
    List<FlightSchedule> findAllFlightSchedules(Long depCode, Long arrCode, LocalDateTime from, LocalDateTime to, Sort sortBy);
}
