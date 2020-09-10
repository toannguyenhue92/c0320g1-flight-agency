package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Promo;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

}
