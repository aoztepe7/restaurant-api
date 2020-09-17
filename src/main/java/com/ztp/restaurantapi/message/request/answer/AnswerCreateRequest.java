package com.ztp.restaurantapi.message.request.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class AnswerCreateRequest {
    @NotNull(message = "Review Id Can Not Be Empty!")
    private UUID reviewId;

    private String ownerAnswer;
}
