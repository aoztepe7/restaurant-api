package com.ztp.restaurantapi.domain.review;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID>, JpaSpecificationExecutor<Review> {
    Review findByIdAndDeletedFalse(UUID id);

    List<Review> findAllByRestaurantIdAndDeletedFalse(UUID restaurantId);
}
