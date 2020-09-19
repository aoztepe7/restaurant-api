package com.ztp.restaurantapi.message.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantListRequest {

    private String name;

    private UUID ownerId;

    private Double rate;

    public Boolean isOwner;

    private Integer page;
}
