package com.ztp.restaurantapi.handler.answer;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.answer.AnswerService;
import com.ztp.restaurantapi.dto.AnswerDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.answer.AnswerDetailRequest;
import com.ztp.restaurantapi.message.response.answer.AnswerDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AnswerDetailHandler implements Handler<AnswerDetailRequest, AnswerDetailResponse> {

    private final AnswerService answerService;

    @Override
    public AnswerDetailResponse execute(AnswerDetailRequest request) {
        Answer answer = answerService.getById(request.getId());

        return AnswerDetailResponse.builder()
                .answer(new AnswerDto(answer))
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
