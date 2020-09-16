package com.ztp.restaurantapi.handler.user;

import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.dto.UserDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.user.UserDetailRequest;
import com.ztp.restaurantapi.message.response.user.UserDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserDetailHandler implements Handler<UserDetailRequest, UserDetailResponse> {

    private final UserService userService;

    @Override
    public UserDetailResponse execute(UserDetailRequest request) {
        User user = userService.getById(request.getId());

        return UserDetailResponse.builder()
                .user(new UserDto(user))
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
