package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.restaurant.*;
import com.ztp.restaurantapi.message.request.restaurant.*;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantDetailResponse;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantListResponse;
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
    private final RestaurantListHandler restaurantListHandler;

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

    @PostMapping("/detail-restaurant")
    public RestaurantDetailResponse detailRestaurant(@Valid @RequestBody RestaurantDetailRequest restaurantDetailRequest){
        return restaurantDetailHandler.execute(restaurantDetailRequest);
    }

    @OwnerRole
    @PostMapping("/create-restaurant")
    public CommonResponse create(@Valid @RequestBody RestaurantCreateRequest restaurantCreateRequest, Authentication authentication){
        return restaurantCreateHandler.execute(restaurantCreateRequest,authentication);
    }

    @PostMapping("/list-restaurant")
    public RestaurantListResponse list(@Valid @RequestBody RestaurantListRequest restaurantListRequest){
        return restaurantListHandler.execute(restaurantListRequest);
    }
}
