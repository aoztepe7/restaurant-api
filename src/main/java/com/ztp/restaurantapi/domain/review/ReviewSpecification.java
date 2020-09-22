package com.ztp.restaurantapi.domain.review;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.message.request.review.ReviewListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ReviewSpecification implements Specification<Review> {

    private final ReviewListRequest reviewListRequest;

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if(reviewListRequest.getUserId() != null){
            Path<User> userPath = root.get("user");
            predicates.add(cb.and(cb.equal(userPath.get("id"),reviewListRequest.getUserId())));
        }

        if(reviewListRequest.getRestaurantId() != null){
            Path<Restaurant> restaurantPath = root.get("restaurant");
            predicates.add(cb.and(cb.equal(restaurantPath.get("id"),reviewListRequest.getRestaurantId())));
        }

        if(reviewListRequest.getRate() != null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("rate"),reviewListRequest.getRate()));
        }

        if(reviewListRequest.getAnswerRequired() != null){
            predicates.add(cb.and(cb.equal(root.get("answerRequired"),reviewListRequest.getRate())));
        }

        predicates.add(cb.and(cb.isNotNull(root.get("deleted"))));
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
