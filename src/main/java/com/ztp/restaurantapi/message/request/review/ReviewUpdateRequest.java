package com.ztp.restaurantapi.message.request.review;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ReviewUpdateRequest {
    @NotNull(message = "Review ID Can Not Be Null!")
    private UUID id;

    @NotNull(message = "Restaurant ID Can Not Be Null!")
    private UUID restaurantId;

    private UUID userId;

    @NotNull(message = "Visit Date Can Not Be Empty!")
    private String visitDate;

    private String comment;
    
    private Boolean answerRequired;

    private Double rate = 5D;
}
