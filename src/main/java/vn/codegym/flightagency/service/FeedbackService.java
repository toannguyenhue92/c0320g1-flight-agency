package vn.codegym.flightagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.flightagency.model.Feedback;

public interface FeedbackService {
    Page<Feedback> findAll(Pageable pageable);
    Feedback findById(Long id);
    void save(Feedback feedback);
}
