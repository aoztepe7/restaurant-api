package com.ztp.restaurantapi.domain.restaurant;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RestaurantRepository extends CrudRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {
    Restaurant findByIdAndDeletedFalse(UUID id);

    //Restaurant findByOwnerIdAndDeletedFalse(UUID id);
}
