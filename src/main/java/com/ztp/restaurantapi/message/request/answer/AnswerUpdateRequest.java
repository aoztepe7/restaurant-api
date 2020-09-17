package com.ztp.restaurantapi.message.request.answer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class AnswerUpdateRequest {
    @NotNull(message = "Answer Id Can Not Be Empty!")
    private UUID id;

    private String ownerAnswer;
}
