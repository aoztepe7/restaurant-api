package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantListRequest;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantListHandler implements Handler<RestaurantListRequest, RestaurantListResponse> {

    private final RestaurantService restaurantService;

    @Override
    public RestaurantListResponse execute(RestaurantListRequest request) {
        Page<RestaurantDto> restaurants = restaurantService.search(request)
                .map(this::convertTo);

        return RestaurantListResponse.builder()
                .restaurants(restaurants)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    private RestaurantDto convertTo(Restaurant restaurant) {
        return new RestaurantDto(restaurant);
    }

}
