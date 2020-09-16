package com.ztp.restaurantapi.message.response.restaurant;

import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDetailResponse extends BaseResponse {
    private RestaurantDto restaurant;

    @Builder
    public RestaurantDetailResponse(ResponseCode responseCode, RestaurantDto restaurant){
        super(responseCode);
        this.restaurant = restaurant;
    }
}
