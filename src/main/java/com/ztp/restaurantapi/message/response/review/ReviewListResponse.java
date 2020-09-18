package com.ztp.restaurantapi.message.response.review;

import com.ztp.restaurantapi.dto.ReviewDto;
import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class ReviewListResponse extends BaseResponse {

    private Page<ReviewDto> reviews;

    @Builder
    public ReviewListResponse(ResponseCode responseCode, Page<ReviewDto> reviews) {
        super(responseCode);
        this.reviews = reviews;
    }

}
