package com.ztp.restaurantapi.handler.review;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.event.RateCalculateEvent;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.review.ReviewUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class ReviewUpdateHandler implements Handler<ReviewUpdateRequest,CommonResponse> {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public CommonResponse execute(ReviewUpdateRequest request) {
        Review review = reviewService.getById(request.getId());
        User user = userService.getById(request.getUserId());
        Restaurant restaurant = restaurantService.getById(request.getRestaurantId());
        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setRate(request.getRate());
        review.setVisitDate(LocalDate.parse(request.getVisitDate()));
        review.setAnswerRequired(request.getAnswerRequired());
        review.setComment(request.getComment());
        reviewService.update(review);
        RateCalculateEvent rateCalculateEvent = new RateCalculateEvent(this,review);
        applicationEventPublisher.publishEvent(rateCalculateEvent);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
