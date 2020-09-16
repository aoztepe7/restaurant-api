package com.ztp.restaurantapi.message.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class UserDetailRequest {
    @NotNull
    private UUID id;
}
