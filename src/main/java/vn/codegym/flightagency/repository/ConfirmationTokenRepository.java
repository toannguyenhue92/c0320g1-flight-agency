package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.flightagency.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);}
