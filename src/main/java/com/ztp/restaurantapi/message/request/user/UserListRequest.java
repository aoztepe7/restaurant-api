package com.ztp.restaurantapi.message.request.user;

import com.ztp.restaurantapi.domain.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListRequest {
    private String firstName;
    private String lastName;
    private String mail;
    private Role role;
    private Integer page;
}
