package com.ztp.restaurantapi.handler.answer;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.answer.AnswerService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.answer.AnswerDeleteRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AnswerDeleteHandler implements Handler<AnswerDeleteRequest,CommonResponse> {

    private final AnswerService answerService;

    @Override
    public CommonResponse execute(AnswerDeleteRequest request) {
        Answer answer = answerService.getById(request.getId());
        answerService.delete(answer);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
