package com.ztp.restaurantapi.message.response;

import com.ztp.restaurantapi.message.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse extends BaseResponse{
    @Builder
    public CommonResponse(ResponseCode responseCode){
        super(responseCode);
    }
}
