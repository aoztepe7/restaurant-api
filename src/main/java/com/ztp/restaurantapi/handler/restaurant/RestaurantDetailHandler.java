package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDetailRequest;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantDetailHandler implements Handler<RestaurantDetailRequest, RestaurantDetailResponse> {

    private final RestaurantService restaurantService;

    @Override
    public RestaurantDetailResponse execute(RestaurantDetailRequest request) {
        Restaurant restaurant = restaurantService.getById(request.getId());

        return RestaurantDetailResponse.builder()
                .restaurant(new RestaurantDto(restaurant))
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
