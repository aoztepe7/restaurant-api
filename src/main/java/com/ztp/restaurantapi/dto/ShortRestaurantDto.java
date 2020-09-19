package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.Utils.Helper;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.review.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ShortRestaurantDto {

    private UUID id;
    private String name;
    private UserDto owner;
    private Double rate;
    private Boolean deleted;

    public ShortRestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.owner = new UserDto(restaurant.getOwner());
        this.rate = restaurant.getRate();
        this.deleted = restaurant.getDeleted();
    }

}
