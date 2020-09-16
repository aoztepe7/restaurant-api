package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantCreateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantCreateHandler {

    private final RestaurantService restaurantService;
    private final UserService userService;

    public CommonResponse execute(RestaurantCreateRequest request, Authentication authentication) {
        User user = userService.getUserByAuth(authentication);
        user.setRole(Role.OWNER);
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setOwner(user);
        restaurant.setRate(request.getRate());
        restaurantService.create(restaurant);
        userService.update(user);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
