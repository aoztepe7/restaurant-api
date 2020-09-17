package com.ztp.restaurantapi.domain.answer;

import com.ztp.restaurantapi.domain.BaseEntity;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.review.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "owner_answer")
    private String ownerAnswer;
}
