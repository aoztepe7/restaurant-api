package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.dto.ShortRestaurantDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantListRequest;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantListHandler {

    private final RestaurantService restaurantService;
    private final UserService userService;

    public RestaurantListResponse execute(RestaurantListRequest request, Authentication authentication) {
        if(request.getIsOwner()){
            request.setOwnerId(userService.getUserByAuth(authentication).getId());
        }
        Page<ShortRestaurantDto> restaurants = restaurantService.search(request)
                .map(this::convertTo);

        return RestaurantListResponse.builder()
                .restaurants(restaurants)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    private ShortRestaurantDto convertTo(Restaurant restaurant) {
        return new ShortRestaurantDto(restaurant);
    }

}
