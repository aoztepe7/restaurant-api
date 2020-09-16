package com.ztp.restaurantapi.handler.user;

import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.handler.Handler;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.request.user.UserUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserUpdateHandler implements Handler<UserUpdateRequest,CommonResponse> {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Override
    public CommonResponse execute(UserUpdateRequest request) {
        User user = userService.getById(request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMail(request.getMail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userService.update(user);

        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }
}
