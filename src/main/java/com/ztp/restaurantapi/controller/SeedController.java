package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.domain.user.User;
import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/seed")
public class SeedController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/seed-admin")
    public CommonResponse seedAdmin() {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setFirstName("ALI");
        user.setLastName("OZTEPE");
        user.setMail("aliztp7@gmail.com");
        userService.create(user);
        return CommonResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .build();
    }

}
