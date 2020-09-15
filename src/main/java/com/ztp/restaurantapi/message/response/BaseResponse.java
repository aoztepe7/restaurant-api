package com.ztp.restaurantapi.message.response;

import com.ztp.restaurantapi.message.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private Integer code;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
