package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Promo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public abstract class PromoRepository implements JpaRepository<Promo,Long> {

}
