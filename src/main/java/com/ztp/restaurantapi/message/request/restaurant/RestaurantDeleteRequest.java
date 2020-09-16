package com.ztp.restaurantapi.message.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class RestaurantDeleteRequest {
    @NotNull
    private UUID id;
}
