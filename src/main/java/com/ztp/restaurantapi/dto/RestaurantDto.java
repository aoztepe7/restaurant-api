package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantDto {

    private UUID id;
    private String name;
    private UserDto owner;
    private Double rate;
    private Boolean deleted;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.owner = new UserDto(restaurant.getOwner());
        this.rate = restaurant.getRate();
        this.deleted = restaurant.getDeleted();
    }

}
