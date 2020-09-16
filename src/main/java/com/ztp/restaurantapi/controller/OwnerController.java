package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.restaurant.RestaurantCreateHandler;
import com.ztp.restaurantapi.message.request.restaurant.RestaurantCreateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final RestaurantCreateHandler restaurantCreateHandler;

    @PostMapping("/create-restaurant")
    public CommonResponse create(@Valid @RequestBody RestaurantCreateRequest restaurantCreateRequest, Authentication authentication){
        return restaurantCreateHandler.execute(restaurantCreateRequest,authentication);
    }
}
