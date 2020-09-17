package com.ztp.restaurantapi.domain.answer;

import java.util.UUID;

public interface AnswerService {
    Answer getById(UUID id);

    void create(Answer answer);

    void delete(Answer answer);

    void update(Answer answer);

    Answer getByReviewAndRestaurant(UUID reviewId,UUID restaurantId);
}
