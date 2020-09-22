package com.ztp.restaurantapi.handler.answer;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.answer.AnswerService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
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
    private final ReviewService reviewService;

    @Override
    public CommonResponse execute(AnswerDeleteRequest request) {
        Answer answer = answerService.getById(request.getId());
        Review review = reviewService.getById(answer.getReview().getId());
        review.setAnswerRequired(true);
        answerService.delete(answer);
        reviewService.update(review);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
