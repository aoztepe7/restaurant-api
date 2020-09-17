package com.ztp.restaurantapi.message.request.review;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ReviewDeleteRequest {
    @NotNull
    private UUID id;
}
