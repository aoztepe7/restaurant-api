package com.ztp.restaurantapi.domain.answer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getById(UUID id) {
        return Optional.ofNullable(answerRepository.findByIdAndDeletedNotNull(id))
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
        return answerRepository.findByReviewIdAndRestaurantIdAndDeletedNotNull(reviewId,restaurantId);
    }

    @Override
    public Answer getByReview(UUID reviewId) {
        return answerRepository.findByReviewIdAndDeletedNotNull(reviewId);
    }
}
