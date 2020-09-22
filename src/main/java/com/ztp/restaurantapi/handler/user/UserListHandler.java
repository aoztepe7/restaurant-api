package com.ztp.restaurantapi.handler.user;

import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.dto.UserDto;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.user.UserListRequest;
import com.ztp.restaurantapi.message.response.user.UserListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserListHandler implements Handler<UserListRequest, UserListResponse> {

    private final UserService userService;

    public UserListResponse execute(UserListRequest request) {
        Page<UserDto> users = userService.search(request)
                .map(this::convertTo);

        return UserListResponse.builder()
                .users(users)
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

    private UserDto convertTo(User user) {
        return new UserDto(user);
    }

}
