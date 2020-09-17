package com.ztp.restaurantapi.handler.review;

import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.dto.ReviewDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.review.ReviewDetailRequest;
import com.ztp.restaurantapi.message.response.review.ReviewDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReviewDetailHandler implements Handler<ReviewDetailRequest, ReviewDetailResponse> {

    private final ReviewService reviewService;

    @Override
    public ReviewDetailResponse execute(ReviewDetailRequest request) {
        Review review = reviewService.getById(request.getId());

        return ReviewDetailResponse.builder()
                .review(new ReviewDto(review))
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
