package com.ztp.restaurantapi.dto;

import com.ztp.restaurantapi.domain.restaurant.Restaurant;
import com.ztp.restaurantapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserPreviewDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String mail;

    public UserPreviewDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.mail = user.getMail();
    }
}
