package com.ztp.restaurantapi.domain.restaurant;

import com.ztp.restaurantapi.message.request.restaurant.RestaurantListRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RestaurantService {
    Restaurant getById(UUID id);

    void create(Restaurant restaurant);

    void delete(Restaurant restaurant);

    void update(Restaurant restaurant);

    Page<Restaurant> search(RestaurantListRequest command);

    //Restaurant getRestaurantByOwnerId(UUID ownerId);
}
