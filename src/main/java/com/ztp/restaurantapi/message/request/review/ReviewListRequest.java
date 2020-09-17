package com.ztp.restaurantapi.message.request.review;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewListRequest {
    private UUID userId;

    private UUID restaurantId;

    private String visitDate;

    private Double rate;

    private Boolean answerRequired;

    private Integer page;
}
