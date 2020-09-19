package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.Utils.Helper;
import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.review.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Getter
@Setter
public class RestaurantDto {

    private UUID id;
    private String name;
    private UserDto owner;
    private String highestRatedReview;
    private Double highestRate;
    private String lowestRatedReview;
    private Double lowestRate;
    private String latestReview;
    private Double latestRate;
    private String latestDate;
    private Double rate;
    private Boolean deleted;

    public RestaurantDto(Restaurant restaurant,Review theHighest,Review theLowest,Review theLatest) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.owner = new UserDto(restaurant.getOwner());
        this.rate = restaurant.getRate();
        this.deleted = restaurant.getDeleted();
        if(theHighest != null){
            this.highestRatedReview =theHighest.getComment();
            this.highestRate =theHighest.getRate();
            this.lowestRatedReview =theLowest.getComment();
            this.lowestRate =theLowest.getRate();
            this.latestReview =theLatest.getComment();
            this.latestRate =theLatest.getRate();
            this.latestDate = Helper.dateFormatter(theLatest.getVisitDate().toString());
        } else {
            this.highestRatedReview = "N/A";
            this.highestRate =0D;
            this.lowestRatedReview ="N/A";
            this.lowestRate =0D;
            this.latestReview ="N/A";
            this.latestRate =0D;
            this.latestDate = "N/A";
        }
    }

}
