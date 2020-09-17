package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.exception.ExceptionMessage;
import com.ztp.restaurantapi.exception.UnauthorizedException;
import com.ztp.restaurantapi.message.response.BaseResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse domainExceptionHandler(BadCredentialsException e) {
        BaseResponse response = new BaseResponse();
        //response.setCode(exceptionMessage.responseCode().getCode());
        //response.setMessage(exceptionMessage.responseCode().getMessage());
        return response;
    }
}
