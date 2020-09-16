package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantUpdateHandler implements Handler<RestaurantUpdateRequest,CommonResponse> {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @Override
    public CommonResponse execute(RestaurantUpdateRequest request) {
        Restaurant restaurant = restaurantService.getById(request.getId());
        User owner = userService.getById(request.getUserId());
        restaurant.setName(request.getName());
        restaurant.setRate(request.getRate());
        restaurant.setOwner(owner);
        restaurantService.update(restaurant);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
