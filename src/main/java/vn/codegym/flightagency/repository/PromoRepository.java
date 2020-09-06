package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

}
