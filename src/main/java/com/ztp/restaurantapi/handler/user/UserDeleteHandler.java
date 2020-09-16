package com.ztp.restaurantapi.handler.user;

import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.user.UserDeleteRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserDeleteHandler implements Handler<UserDeleteRequest,CommonResponse> {

    private final UserService userService;

    @Override
    public CommonResponse execute(UserDeleteRequest request) {
        User user = userService.getById(request.getId());
        userService.delete(user);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
