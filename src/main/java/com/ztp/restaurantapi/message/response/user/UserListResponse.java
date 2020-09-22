package com.ztp.restaurantapi.message.response.user;

import com.ztp.restaurantapi.dto.UserDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class UserListResponse extends BaseResponse {

    private Page<UserDto> users;

    @Builder
    public UserListResponse(ResponseCode responseCode, Page<UserDto> users) {
        super(responseCode);
        this.users = users;
    }

}
