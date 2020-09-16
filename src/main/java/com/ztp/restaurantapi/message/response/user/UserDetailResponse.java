package com.ztp.restaurantapi.message.response.user;

import com.ztp.restaurantapi.dto.UserDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponse extends BaseResponse {
    private UserDto user;

    @Builder
    public UserDetailResponse(ResponseCode responseCode,UserDto user){
        super(responseCode);
        this.user = user;
    }
}
