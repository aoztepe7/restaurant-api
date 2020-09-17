package com.ztp.restaurantapi.handler.answer;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.answer.AnswerService;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.answer.AnswerCreateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class AnswerCreateHandler {

    private final AnswerService answerService;
    private final ReviewService reviewService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public CommonResponse execute(AnswerCreateRequest request, Authentication authentication) {
        User user = userService.getUserByAuth(authentication);
        Restaurant restaurant = restaurantService.getRestaurantByOwnerId(user.getId());

        Answer existingAnswer = answerService.getByReviewAndRestaurant(request.getReviewId(),restaurant.getId());

        if(Objects.isNull(existingAnswer)){
            Review review = reviewService.getById(request.getReviewId());
            Answer answer = new Answer();
            answer.setRestaurant(restaurant);
            answer.setReview(review);
            answer.setOwnerAnswer(request.getOwnerAnswer());
            answerService.create(answer);

            return CommonResponse.builder()
                    .responseCode(ResponseCode.SUCCESS)
                    .build();
        }
        else {
            return CommonResponse.builder()
                    .responseCode(ResponseCode.MAX_ANSWER_LIMIT)
                    .build();
        }
    }
}
