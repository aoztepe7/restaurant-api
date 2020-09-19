package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDetailRequest;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Component
public class RestaurantDetailHandler implements Handler<RestaurantDetailRequest, RestaurantDetailResponse> {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    @Override
    public RestaurantDetailResponse execute(RestaurantDetailRequest request) {
        Restaurant restaurant = restaurantService.getById(request.getId());

        List<Review> reviewList = reviewService.getAllReviewsByRestaurant(restaurant.getId());
        Review theHighest = null;
        Review theLowest = null;
        Review theLatest = null;

        if(reviewList.size()>0){
            theHighest = new Review();
            theLowest = new Review();
            theLatest = new Review();
            theHighest =  Collections.max(reviewList, Comparator.comparing(s -> s.getRate()));
            theLowest = Collections.min(reviewList,Comparator.comparing(s -> s.getRate()));
            theLatest = Collections.max(reviewList,Comparator.comparing(s -> s.getVisitDate()));
        }

        return RestaurantDetailResponse.builder()
                .restaurant(new RestaurantDto(restaurant,theHighest,theLowest,theLatest))
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
