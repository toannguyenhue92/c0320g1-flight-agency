package vn.codegym.flightagency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.flightagency.model.Feedback;
import vn.codegym.flightagency.repository.FeedbackRepository;
import vn.codegym.flightagency.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
