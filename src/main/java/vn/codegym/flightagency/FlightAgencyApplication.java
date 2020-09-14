package vn.codegym.flightagency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.codegym.flightagency.model.Bill;
import vn.codegym.flightagency.repository.BillRepository;

@SpringBootApplication
public class FlightAgencyApplication {


    public static void main(String[] args) {

        SpringApplication.run(FlightAgencyApplication.class, args);


    }



}
