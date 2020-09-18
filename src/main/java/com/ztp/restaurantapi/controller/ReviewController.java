package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.domain.user.UserService;
import com.ztp.restaurantapi.handler.review.*;
import com.ztp.restaurantapi.message.request.review.*;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.review.ReviewDetailResponse;
import com.ztp.restaurantapi.message.response.review.ReviewListResponse;
import com.ztp.restaurantapi.security.AdminRole;
import com.ztp.restaurantapi.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private final ReviewListHandler reviewListHandler;
    private final UserService userService;

    @UserRole
    @PostMapping("/create")
    public CommonResponse createReview(@Valid @RequestBody ReviewCreateRequest reviewCreateRequest, Authentication authentication){
        return reviewCreateHandler.execute(reviewCreateRequest,authentication);
    }

    @PostMapping("/update")
    public CommonResponse updateReview(@Valid @RequestBody ReviewUpdateRequest reviewUpdateRequest,Authentication authentication){
        reviewUpdateRequest.setUserId(userService.getUserByAuth(authentication).getId());
        return reviewUpdateHandler.execute(reviewUpdateRequest);
    }

    @PostMapping("/detail")
    public ReviewDetailResponse detailReview(@Valid @RequestBody ReviewDetailRequest reviewDetailRequest){
        return reviewDetailHandler.execute(reviewDetailRequest);
    }

    @PostMapping("/delete")
    public CommonResponse deleteReview(@Valid @RequestBody ReviewDeleteRequest reviewDeleteRequest){
        return reviewDeleteHandler.execute(reviewDeleteRequest);
    }

    @PostMapping("/list")
    public ReviewListResponse listReview(@Valid @RequestBody ReviewListRequest reviewListRequest, Authentication authentication){
        return reviewListHandler.execute(reviewListRequest,authentication);
    }
}
