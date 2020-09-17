package com.ztp.restaurantapi.domain.answer;

import com.ztp.restaurantapi.domain.review.*;
import com.ztp.restaurantapi.message.request.review.ReviewListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getById(UUID id) {
        return Optional.ofNullable(answerRepository.findByIdAndDeletedFalse(id))
                .orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public void create(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(Answer answer) {
        getById(answer.getId());
        answer.setDeleted(null);
        answerRepository.save(answer);
    }

    @Override
    public void update(Answer answer) {
        getById(answer.getId());
        answerRepository.save(answer);
    }

    @Override
    public Answer getByReviewAndRestaurant(UUID reviewId, UUID restaurantId) {
        return answerRepository.findByReviewIdAndRestaurantIdAndDeletedFalse(reviewId,restaurantId);
    }
}
