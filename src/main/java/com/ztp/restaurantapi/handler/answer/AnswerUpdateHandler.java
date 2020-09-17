package com.ztp.restaurantapi.handler.answer;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.answer.AnswerService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.answer.AnswerUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AnswerUpdateHandler implements Handler<AnswerUpdateRequest,CommonResponse> {

    private final AnswerService answerService;

    public CommonResponse execute(AnswerUpdateRequest request) {
        Answer answer = answerService.getById(request.getId());
        answer.setOwnerAnswer(request.getOwnerAnswer());
        answerService.update(answer);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
