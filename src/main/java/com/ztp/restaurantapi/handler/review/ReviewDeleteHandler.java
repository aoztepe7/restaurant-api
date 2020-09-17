package com.ztp.restaurantapi.handler.review;

import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.event.RateCalculateEvent;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.review.ReviewDeleteRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReviewDeleteHandler implements Handler<ReviewDeleteRequest,CommonResponse> {

    private final ReviewService reviewService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public CommonResponse execute(ReviewDeleteRequest request) {
        Review review = reviewService.getById(request.getId());
        reviewService.delete(review);
        RateCalculateEvent rateCalculateEvent = new RateCalculateEvent(this,review);
        applicationEventPublisher.publishEvent(rateCalculateEvent);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
