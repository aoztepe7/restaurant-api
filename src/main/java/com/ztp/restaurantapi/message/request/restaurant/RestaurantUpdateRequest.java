package com.ztp.restaurantapi.message.request.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class RestaurantUpdateRequest {
    @NotNull(message = "Id Can Not Be Empty!")
    private UUID id;

    @NotEmpty(message = "Name Can Not Be Empty!")
    private String name;

    @NotNull(message = "User Id Can Not Be Empty!")
    private UUID userId;

    private Double rate = 5D;
}
