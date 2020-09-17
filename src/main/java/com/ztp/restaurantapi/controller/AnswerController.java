package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.handler.answer.AnswerCreateHandler;
import com.ztp.restaurantapi.handler.answer.AnswerDeleteHandler;
import com.ztp.restaurantapi.handler.answer.AnswerDetailHandler;
import com.ztp.restaurantapi.handler.answer.AnswerUpdateHandler;
import com.ztp.restaurantapi.message.request.answer.AnswerCreateRequest;
import com.ztp.restaurantapi.message.request.answer.AnswerDeleteRequest;
import com.ztp.restaurantapi.message.request.answer.AnswerDetailRequest;
import com.ztp.restaurantapi.message.request.answer.AnswerUpdateRequest;
import com.ztp.restaurantapi.message.response.CommonResponse;
import com.ztp.restaurantapi.message.response.answer.AnswerDetailResponse;
import com.ztp.restaurantapi.security.AdminRole;
import com.ztp.restaurantapi.security.OwnerRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {

    private final AnswerCreateHandler answerCreateHandler;
    private final AnswerUpdateHandler answerUpdateHandler;
    private final AnswerDeleteHandler answerDeleteHandler;
    private final AnswerDetailHandler answerDetailHandler;

    @OwnerRole
    @PostMapping("/create")
    public CommonResponse createAnswer(@Valid @RequestBody AnswerCreateRequest answerCreateRequest, Authentication authentication){
        return answerCreateHandler.execute(answerCreateRequest,authentication);
    }

    @AdminRole
    @PostMapping("/update")
    public CommonResponse updateAnswer(@Valid @RequestBody AnswerUpdateRequest answerUpdateRequest){
        return answerUpdateHandler.execute(answerUpdateRequest);
    }

    @AdminRole
    @PostMapping("/delete")
    public CommonResponse deleteAnswer(@Valid @RequestBody AnswerDeleteRequest answerDeleteRequest){
        return answerDeleteHandler.execute(answerDeleteRequest);
    }

    @PostMapping("/detail")
    public AnswerDetailResponse detailAnswer(@Valid @RequestBody AnswerDetailRequest answerDetailRequest){
        return answerDetailHandler.execute(answerDetailRequest);
    }
}
