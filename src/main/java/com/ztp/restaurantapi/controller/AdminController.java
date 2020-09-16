package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.restaurant.RestaurantDeleteHandler;
import com.ztp.restaurantapi.handler.restaurant.RestaurantDetailHandler;
import com.ztp.restaurantapi.handler.restaurant.RestaurantUpdateHandler;
import com.ztp.restaurantapi.handler.user.UserDeleteHandler;
import com.ztp.restaurantapi.handler.user.UserDetailHandler;
import com.ztp.restaurantapi.handler.user.UserUpdateHandler;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDeleteRequest;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantDetailRequest;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantUpdateRequest;
import com.ztp.restaurantapi.message.request.user.UserDeleteRequest;
import com.ztp.restaurantapi.message.request.user.UserDetailRequest;
import com.ztp.restaurantapi.message.request.user.UserUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.restaurant.RestaurantDetailResponse;
import com.ztp.restaurantapi.message.response.user.UserDetailResponse;
import com.ztp.restaurantapi.security.AdminRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final RestaurantUpdateHandler restaurantUpdateHandler;
    private final RestaurantDeleteHandler restaurantDeleteHandler;
    private final RestaurantDetailHandler restaurantDetailHandler;
    private final UserUpdateHandler userUpdateHandler;
    private final UserDeleteHandler userDeleteHandler;
    private final UserDetailHandler userDetailHandler;

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

    @AdminRole
    @PostMapping("/update-user")
    public CommonResponse updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return userUpdateHandler.execute(userUpdateRequest);
    }

    @AdminRole
    @PostMapping("/delete-user")
    public CommonResponse deleteUser(@Valid @RequestBody UserDeleteRequest userDeleteRequest){
        return userDeleteHandler.execute(userDeleteRequest);
    }

    @AdminRole
    @PostMapping("/detail-user")
    public UserDetailResponse detailUser(@Valid @RequestBody UserDetailRequest userDetailRequest){
        return userDetailHandler.execute(userDetailRequest);
    }
}
