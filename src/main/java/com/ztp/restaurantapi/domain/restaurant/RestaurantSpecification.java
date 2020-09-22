package com.ztp.restaurantapi.domain.restaurant;

import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RestaurantSpecification implements Specification<Restaurant> {

    private final RestaurantListRequest restaurantListRequest;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(restaurantListRequest.getName())){
            predicates.add(cb.like(cb.upper(root.get("name")),"%"+restaurantListRequest.getName().toUpperCase()+"%"));
        }

        if(restaurantListRequest.getOwnerId() != null){
            Path<User> userPath = root.get("owner");
            predicates.add(cb.and(cb.equal(userPath.get("id"),restaurantListRequest.getOwnerId())));
        }

        if(restaurantListRequest.getRate() != null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("rate"),restaurantListRequest.getRate()));
        }

        predicates.add(cb.and(cb.isNotNull(root.get("deleted"))));
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
