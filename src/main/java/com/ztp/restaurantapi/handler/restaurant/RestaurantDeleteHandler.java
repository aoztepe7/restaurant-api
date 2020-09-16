package com.ztp.restaurantapi.handler.restaurant;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.restaurant.RestaurantService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDeleteRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestaurantDeleteHandler implements Handler<RestaurantDeleteRequest,CommonResponse> {

    private final RestaurantService restaurantService;

    @Override
    public CommonResponse execute(RestaurantDeleteRequest request) {
        Restaurant restaurant = restaurantService.getById(request.getId());
        restaurantService.delete(restaurant);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
