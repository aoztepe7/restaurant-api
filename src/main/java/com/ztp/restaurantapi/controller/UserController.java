package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.handler.user.UserCreateHandler;
import com.ztp.restaurantapi.message.request.user.UserCreateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.security.AdminRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserCreateHandler userCreateHandler;

    @PostMapping("/register")
    public CommonResponse register(@Valid @RequestBody UserCreateRequest request){
        return userCreateHandler.execute(request);
    }
}
