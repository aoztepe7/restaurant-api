package com.ztp.restaurantapi.event;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.review.Review;
import com.ztp.restaurantapi.domain.review.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RateCalculateListener {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;

    @Async
    @EventListener
    public void handleCalculateEvent(RateCalculateEvent rateCalculateEvent){
        Restaurant restaurant = rateCalculateEvent.getReview().getRestaurant();
        List<Review> reviewList = reviewService.getAllReviewsByRestaurant(restaurant.getId());
        int reviewCount = reviewList.size() + 1;
        restaurant.setRate(finalRate(reviewList,reviewCount));
        restaurantService.update(restaurant);
    }

    private double finalRate(List<Review> reviewList,int reviewCount){
        double totalReviewRate = 5D;
        for(Review review : reviewList){
            totalReviewRate += review.getRate();
        }
        return totalReviewRate / reviewCount;
    }
}
