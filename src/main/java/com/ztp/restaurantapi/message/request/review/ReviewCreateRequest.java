package com.ztp.restaurantapi.message.request.review;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ReviewCreateRequest {
    @NotNull(message = "Restaurant ID Can Not Be Null!")
    private UUID restaurantId;

    @NotNull(message = "Visit Date Can Not Be Empty!")
    private String visitDate;

    private String comment;

    private Double rate = 5D;
}
