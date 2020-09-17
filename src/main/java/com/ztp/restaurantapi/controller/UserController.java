package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.handler.user.UserCreateHandler;
import com.ztp.restaurantapi.handler.user.UserDeleteHandler;
import com.ztp.restaurantapi.handler.user.UserDetailHandler;
import com.ztp.restaurantapi.handler.user.UserUpdateHandler;
import com.ztp.restaurantapi.message.request.user.UserCreateRequest;
import com.ztp.restaurantapi.message.request.user.UserDeleteRequest;
import com.ztp.restaurantapi.message.request.user.UserDetailRequest;
import com.ztp.restaurantapi.message.request.user.UserUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.user.UserDetailResponse;
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
    private final UserUpdateHandler userUpdateHandler;
    private final UserDeleteHandler userDeleteHandler;
    private final UserDetailHandler userDetailHandler;

    @PostMapping("/register")
    public CommonResponse register(@Valid @RequestBody UserCreateRequest request){
        request.setRole(Role.USER);
        return userCreateHandler.execute(request);
    }

    @AdminRole
    @PostMapping("/api/v1/user/update-user")
    public CommonResponse updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return userUpdateHandler.execute(userUpdateRequest);
    }

    @AdminRole
    @PostMapping("/api/v1/user/delete-user")
    public CommonResponse deleteUser(@Valid @RequestBody UserDeleteRequest userDeleteRequest){
        return userDeleteHandler.execute(userDeleteRequest);
    }

    @AdminRole
    @PostMapping("/api/v1/user/detail-user")
    public UserDetailResponse detailUser(@Valid @RequestBody UserDetailRequest userDetailRequest){
        return userDetailHandler.execute(userDetailRequest);
    }
}
