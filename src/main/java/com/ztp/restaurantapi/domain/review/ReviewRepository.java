package com.ztp.restaurantapi.domain.review;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID>, JpaSpecificationExecutor<Review> {
    Review findByIdAndDeletedNotNull(UUID id);

    List<Review> findAllByRestaurantIdAndDeletedNotNull(UUID restaurantId);
}
