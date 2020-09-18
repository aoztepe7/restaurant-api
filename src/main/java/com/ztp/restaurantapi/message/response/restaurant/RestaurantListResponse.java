package com.ztp.restaurantapi.message.response.restaurant;

import com.ztp.restaurantapi.dto.RestaurantDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class RestaurantListResponse extends BaseResponse {

    private Page<RestaurantDto> restaurants;

    @Builder
    public RestaurantListResponse(ResponseCode responseCode, Page<RestaurantDto> restaurants) {
        super(responseCode);
        this.restaurants = restaurants;
    }

}
