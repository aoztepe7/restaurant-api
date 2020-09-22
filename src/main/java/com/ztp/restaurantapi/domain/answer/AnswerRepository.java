package com.ztp.restaurantapi.domain.answer;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface AnswerRepository extends CrudRepository<Answer, UUID>{
    Answer findByIdAndDeletedNotNull(UUID id);

    Answer findByReviewIdAndRestaurantIdAndDeletedNotNull(UUID reviewId,UUID restaurantId);

    Answer findByReviewIdAndDeletedNotNull(UUID reviewId);
}
