package vn.codegym.flightagency.service.search;

import org.springframework.data.jpa.domain.Specification;
import vn.codegym.flightagency.model.*;

import javax.persistence.criteria.*;

// Thành Long
public class TransactionDetailSpecification implements Specification<TransactionDetail> {
    private final SearchCriteria criteria;

    public TransactionDetailSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
    @Override
    public Predicate toPredicate(Root<TransactionDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(criteria.getOperation().equalsIgnoreCase("like")) {
            // value like %chuỗi_tìm_kiếm%
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValues().get(0) + "%");
        } else if(criteria.getOperation().equalsIgnoreCase("passenger-join")) {
            Join<TransactionDetail, Passenger> passengerJoin = root.join("passenger"); // inner join.
            return criteriaBuilder.equal(passengerJoin.get(criteria.getKey()), criteria.getValues().get(0));
        } else if(criteria.getOperation().equalsIgnoreCase("transaction-join")) {
            Join<TransactionDetail, Transaction> transactionJoin = root.join("transaction"); // inner join.
            Join<Transaction, FlightSchedule> flightScheduleJoin = transactionJoin.join("flightSchedule");
            Join<FlightSchedule, Airport> airportJoin = flightScheduleJoin.join("airport");
            return criteriaBuilder.equal(airportJoin.get(criteria.getKey()), criteria.getValues().get(0));
        } else {
            return null;
        }
    }
}
