package com.ztp.restaurantapi.domain.answer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends CrudRepository<Answer, UUID>{
    Answer findByIdAndDeletedFalse(UUID id);

    Answer findByReviewIdAndRestaurantIdAndDeletedFalse(UUID reviewId,UUID restaurantId);

    Answer findByReviewIdAndDeletedFalse(UUID reviewId);
}
