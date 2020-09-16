package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.domain.user.Role;
import com.ztp.restaurantapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private Role role;
    private Boolean deleted;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mail = user.getMail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.deleted = user.getDeleted();
    }

}
