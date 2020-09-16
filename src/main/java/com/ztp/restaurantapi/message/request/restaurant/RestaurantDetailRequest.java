package com.ztp.restaurantapi.message.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class RestaurantDetailRequest {
    @NotNull
    private UUID id;
}
