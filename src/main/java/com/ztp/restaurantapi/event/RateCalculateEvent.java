package com.ztp.restaurantapi.event;

import com.ztp.restaurantapi.domain.review.Review;
import org.springframework.context.ApplicationEvent;

public class RateCalculateEvent extends ApplicationEvent {

    private Review review;

    public RateCalculateEvent(Object source,Review review) {
        super(source);
        this.review = review;
    }

    public Review getReview(){
        return review;
    }
}
