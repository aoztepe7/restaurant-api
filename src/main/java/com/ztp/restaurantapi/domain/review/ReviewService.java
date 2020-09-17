package com.ztp.restaurantapi.domain.review;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.message.request.review.ReviewListRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    Review getById(UUID id);

    void create(Review review);

    void delete(Review review);

    void update(Review review);

    Page<Review> search(ReviewListRequest command);

    List<Review> getAllReviewsByRestaurant(UUID restaurantId);
}
