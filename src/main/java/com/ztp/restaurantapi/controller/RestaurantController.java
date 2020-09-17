package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.restaurant.RestaurantCreateHandler;
import com.ztp.restaurantapi.handler.restaurant.RestaurantDeleteHandler;
import com.ztp.restaurantapi.handler.restaurant.RestaurantDetailHandler;
import com.ztp.restaurantapi.handler.restaurant.RestaurantUpdateHandler;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantCreateRequest;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDeleteRequest;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDetailRequest;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantDetailResponse;
import com.ztp.restaurantapi.security.AdminRole;
import com.ztp.restaurantapi.security.OwnerRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    private final RestaurantUpdateHandler restaurantUpdateHandler;
    private final RestaurantDeleteHandler restaurantDeleteHandler;
    private final RestaurantDetailHandler restaurantDetailHandler;
    private final RestaurantCreateHandler restaurantCreateHandler;

    @AdminRole
    @PostMapping("/update-restaurant")
    public CommonResponse updateRestaurant(@Valid @RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return restaurantUpdateHandler.execute(restaurantUpdateRequest);
    }

    @AdminRole
    @PostMapping("/delete-restaurant")
    public CommonResponse deleteRestaurant(@Valid @RequestBody RestaurantDeleteRequest restaurantDeleteRequest){
        return restaurantDeleteHandler.execute(restaurantDeleteRequest);
    }

    @AdminRole
    @PostMapping("/detail-restaurant")
    public RestaurantDetailResponse detailRestaurant(@Valid @RequestBody RestaurantDetailRequest restaurantDetailRequest){
        return restaurantDetailHandler.execute(restaurantDetailRequest);
    }

    @OwnerRole
    @PostMapping("/create-restaurant")
    public CommonResponse create(@Valid @RequestBody RestaurantCreateRequest restaurantCreateRequest, Authentication authentication){
        return restaurantCreateHandler.execute(restaurantCreateRequest,authentication);
    }
}
