package com.ztp.restaurantapi.domain.review;

import com.ztp.restaurantapi.domain.BaseEntity;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate = LocalDate.now();

    @Column(name = "rate")
    private Double rate;

    @Column(name = "answer_required")
    private Boolean answerRequired = true;

    @Column(name = "comment")
    private String comment;
}
