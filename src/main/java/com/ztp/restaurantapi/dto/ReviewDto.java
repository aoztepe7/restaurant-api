package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.Utils.Helper;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.review.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewDto {

    private UUID id;
    private UserPreviewDto user;
    private Restaurant restaurant;
    private String visitDate;
    private Boolean answerRequired;
    private String comment;
    private Double rate;
    private Boolean deleted;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.user = new UserPreviewDto(review.getUser());
        this.restaurant = review.getRestaurant();
        this.visitDate = Helper.dateFormatter(review.getVisitDate().toString());
        this.answerRequired = review.getAnswerRequired();
        this.comment = review.getComment();
        this.rate = review.getRate();
        this.deleted = restaurant.getDeleted();
    }
}
