package com.ztp.restaurantapi.message.response;

import com.ztp.restaurantapi.message.ResponseCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse extends BaseResponse{
    private String token;
    private String role;

    @Builder
    public LoginResponse(ResponseCode responseCode, String token,String role){
        super(responseCode);
        this.token = token;
        this.role = role;
    }
}
