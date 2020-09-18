package com.ztp.restaurantapi.handler.review;

import com.ztp.restaurantapi.Utils.Helper;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.event.RateCalculateEvent;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.review.ReviewCreateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class ReviewCreateHandler {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CommonResponse execute(ReviewCreateRequest request, Authentication authentication) {
        User user = userService.getUserByAuth(authentication);
        Restaurant restaurant = restaurantService.getById(request.getRestaurantId());
        Review review = new Review();
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setComment(request.getComment());
        review.setAnswerRequired(true);
        review.setRate(request.getRate());
        review.setVisitDate(LocalDate.parse(Helper.dateFormatForDb(request.getVisitDate())));
        review.setRate(request.getRate());
        reviewService.create(review);

        RateCalculateEvent rateCalculateEvent = new RateCalculateEvent(this,review);
        applicationEventPublisher.publishEvent(rateCalculateEvent);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
