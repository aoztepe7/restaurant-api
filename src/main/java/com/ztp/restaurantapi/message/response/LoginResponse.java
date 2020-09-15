package com.ztp.restaurantapi.message.response;

import com.ztp.restaurantapi.message.ResponseCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse extends BaseResponse{
    private String token;

    @Builder
    public LoginResponse(ResponseCode responseCode, String token){
        super(responseCode);
        this.token = token;
    }
}
