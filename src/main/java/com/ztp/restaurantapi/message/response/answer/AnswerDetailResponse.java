package com.ztp.restaurantapi.message.response.answer;

import com.ztp.restaurantapi.dto.AnswerDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDetailResponse extends BaseResponse {
    private AnswerDto answer;

    @Builder
    public AnswerDetailResponse(ResponseCode responseCode, AnswerDto answer){
        super(responseCode);
        this.answer = answer;
    }
}
