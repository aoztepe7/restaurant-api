package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.domain.answer.Answer;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerDto {

    private UUID id;
    private Restaurant restaurant;
    private ReviewDto review;
    private String ownerAnswer;
    private Boolean deleted;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.restaurant = answer.getRestaurant();
        this.review = new ReviewDto(answer.getReview());
        this.ownerAnswer = answer.getOwnerAnswer();
        this.deleted = restaurant.getDeleted();
    }
}
