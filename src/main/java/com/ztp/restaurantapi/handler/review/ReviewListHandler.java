package com.ztp.restaurantapi.handler.review;

import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.dto.ReviewDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.review.ReviewListRequest;
import com.ztp.restaurantapi.message.response.review.ReviewListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReviewListHandler  {

    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewListResponse execute(ReviewListRequest request, Authentication authentication) {
        User user = userService.getUserByAuth(authentication);
        if(user.getRole().equals(Role.USER)){
            request.setUserId(user.getId());
        }
        Page<ReviewDto> reviews = reviewService.search(request)
                .map(this::convertTo);

        return ReviewListResponse.builder()
                .reviews(reviews)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    private ReviewDto convertTo(Review review) {
        return new ReviewDto(review);
    }

}
