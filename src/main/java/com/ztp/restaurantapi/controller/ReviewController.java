package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.review.ReviewCreateHandler;
import com.ztp.restaurantapi.handler.review.ReviewDeleteHandler;
import com.ztp.restaurantapi.handler.review.ReviewDetailHandler;
import com.ztp.restaurantapi.handler.review.ReviewUpdateHandler;
import com.ztp.restaurantapi.message.request.review.ReviewCreateRequest;
import com.ztp.restaurantapi.message.request.review.ReviewDeleteRequest;
import com.ztp.restaurantapi.message.request.review.ReviewDetailRequest;
import com.ztp.restaurantapi.message.request.review.ReviewUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.review.ReviewDetailResponse;
import com.ztp.restaurantapi.security.AdminRole;
import com.ztp.restaurantapi.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewCreateHandler reviewCreateHandler;
    private final ReviewUpdateHandler reviewUpdateHandler;
    private final ReviewDetailHandler reviewDetailHandler;
    private final ReviewDeleteHandler reviewDeleteHandler;

    @UserRole
    @PostMapping("/create")
    public CommonResponse createReview(@Valid @RequestBody ReviewCreateRequest reviewCreateRequest, Authentication authentication){
        return reviewCreateHandler.execute(reviewCreateRequest,authentication);
    }

    @AdminRole
    @PostMapping("/update")
    public CommonResponse updateReview(@Valid @RequestBody ReviewUpdateRequest reviewUpdateRequest){
        return reviewUpdateHandler.execute(reviewUpdateRequest);
    }

    @PostMapping("/detail")
    public ReviewDetailResponse detailReview(@Valid @RequestBody ReviewDetailRequest reviewDetailRequest){
        return reviewDetailHandler.execute(reviewDetailRequest);
    }

    @AdminRole
    @PostMapping("/delete")
    public CommonResponse deleteReview(@Valid @RequestBody ReviewDeleteRequest reviewDeleteRequest){
        return reviewDeleteHandler.execute(reviewDeleteRequest);
    }
}
