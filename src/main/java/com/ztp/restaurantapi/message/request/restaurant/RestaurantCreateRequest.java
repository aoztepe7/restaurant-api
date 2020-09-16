package com.ztp.restaurantapi.message.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class RestaurantCreateRequest {
    @NotEmpty(message = "Name Can Not Be Empty!")
    private String name;

    private Double rate = 5D;
}
