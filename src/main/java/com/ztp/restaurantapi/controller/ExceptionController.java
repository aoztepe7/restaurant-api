package com.ztp.restaurantapi.controller;

import com.ztp.restaurantapi.message.ResponseCode;
import com.ztp.restaurantapi.message.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse processValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError error = bindingResult.getFieldError();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseCode.INVALID_REQUEST.getCode());
        baseResponse.setMessage( error.getDefaultMessage());
        return baseResponse;
    }

}
