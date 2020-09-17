package com.ztp.restaurantapi.message.response.review;

import com.ztp.restaurantapi.dto.ReviewDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDetailResponse extends BaseResponse {
    private ReviewDto review;

    @Builder
    public ReviewDetailResponse(ResponseCode responseCode, ReviewDto review){
        super(responseCode);
        this.review = review;
    }
}
