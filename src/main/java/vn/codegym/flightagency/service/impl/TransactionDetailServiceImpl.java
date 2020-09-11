package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.dto.BookingDTO;
import vn.codegym.flightagency.dto.CheckinDto;
import vn.codegym.flightagency.dto.PassengerInfoDTO;
import vn.codegym.flightagency.model.Passenger;
import vn.codegym.flightagency.model.Transaction;
import vn.codegym.flightagency.model.TransactionDetail;
import vn.codegym.flightagency.repository.TransactionDetailRepository;
import vn.codegym.flightagency.service.PassengerService;
import vn.codegym.flightagency.service.TransactionDetailService;
import vn.codegym.flightagency.service.TransactionService;
import vn.codegym.flightagency.service.search.SearchCriteria;
import vn.codegym.flightagency.service.search.TransactionDetailSpecification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PassengerService passengerService;

    // Creator: Duy
    @Override
    public void saveTransactionDetail(BookingDTO booking) {
        // transfer to passenger
        // add passenger
        List<Passenger> passengerList = passengerService.addAllPassengers(booking.getDepPassengers());
        Transaction depTransaction = transactionService.createTransaction(booking.getDepFlightId(), booking.getAccountId(), booking.getDepTotalPrice());
        Transaction finalDepTransaction =  transactionService.save(depTransaction);
        passengerList.forEach((val) -> {
            addDetail(finalDepTransaction, val, booking.getDepPassengers());
        });
        // if round-trip
        if (booking.getRetFlightId() != 0) {
            passengerList = passengerService.addAllPassengers(booking.getRetPassengers());
            Transaction retTransaction = transactionService.createTransaction(booking.getRetFlightId(), booking.getAccountId(), booking.getRetTotalPrice());
            Transaction finalRetTransaction =  transactionService.save(retTransaction);
            passengerList.forEach((val) -> {
                addDetail(finalRetTransaction, val, booking.getRetPassengers());
            });
        }
    }

    // Thành Long
    private CheckinDto transferToDTO(TransactionDetail temp) {
        CheckinDto transactionDetail = new CheckinDto();
        transactionDetail.setBookingCode(temp.getTransaction().getId());
        transactionDetail.setFullName(temp.getPassenger().getFullName());
        transactionDetail.setDeparture(temp.getTransaction().getFlightSchedule().getDepartureAirport());
        transactionDetail.setArrival(temp.getTransaction().getFlightSchedule().getArrivalAirport());
        transactionDetail.setCheckin(temp.getPassenger().getCheckin());
        return transactionDetail;
    }

    // Thành Long
    private Page<CheckinDto> transferToNewPage(Page<TransactionDetail> transactionDetails) {
        TransactionDetail temp;
        List<CheckinDto> transactionDetailDto = new ArrayList<>();
        Iterator iterator = transactionDetails.iterator();
        while (iterator.hasNext()) {
            temp = (TransactionDetail) iterator.next();
            transactionDetailDto.add(transferToDTO(temp));
        }
        Page<CheckinDto> _transactionDetail = new PageImpl<>(transactionDetailDto, transactionDetails.getPageable(), transactionDetails.getTotalElements());
        return _transactionDetail;
    }

    // Thành Long
    @Override
    public Page<CheckinDto> findTransactionDetailByCriteria(Specification<TransactionDetail> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<TransactionDetail> transactionDetails = transactionDetailRepository.findAll(spec, pageable);
        return transferToNewPage(transactionDetails);
    }

    // Thành Long
    @Override
    public Specification<TransactionDetail> getFilter(String bookingCode, String fullName) {
        List<TransactionDetailSpecification> specs = new ArrayList<>();
        Specification<TransactionDetail> spec;
        // search theo name
        if(!"".equals(bookingCode) && !"undefined".equals(bookingCode)) {
            specs.add(new TransactionDetailSpecification(new SearchCriteria("bookingCode", "like", bookingCode)));
        }
        // search theo address
        if(!"".equals(fullName) && !"undefined".equals(fullName) ) {
            specs.add(new TransactionDetailSpecification(new SearchCriteria("fullName", "like", fullName)));
        }
        if (specs.size() != 0) {
            spec = Specification.where(specs.get(0));
            for (int i = 1; i < specs.size(); i++) {
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

    @Override
    public Page<CheckinDto> findAllTransactionDetail(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<TransactionDetail> transactionDetails = transactionDetailRepository.findAll(pageable);
        return transferToNewPage(transactionDetails);
    }

    // Creator: Duy
    private void addDetail(Transaction transaction, Passenger passenger, List<PassengerInfoDTO> baggageInfo) {
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTransaction(transaction);
        transactionDetail.setPassenger(passenger);
        baggageInfo.forEach((val) -> {
            if(val.getFullName().equals(passenger.getFullName())) {
                transactionDetail.setBaggage(getBaggage(val.getBaggagePrice()));
            }
        });
        transactionDetailRepository.save(transactionDetail);
    }

    // Creator: Duy
    private byte getBaggage(int money) {
        switch (money) {
            case 170000:
                return 15;
            case 225000:
                return 20;
            case 275000:
                return 25;
            default:
                return 7;
        }
    }
}
