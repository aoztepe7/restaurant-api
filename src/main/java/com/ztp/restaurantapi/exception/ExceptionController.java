package com.ztp.restaurantapi.exception;

import com.ztp.restaurantapi.message.response.BaseResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse domainExceptionHandler(DomainException e) {
        ExceptionMessage exceptionMessage = AnnotationUtils.findAnnotation(e.getClass(), ExceptionMessage.class);
        BaseResponse response = new BaseResponse();
        response.setCode(exceptionMessage.responseCode().getCode());
        response.setMessage(exceptionMessage.responseCode().getMessage());
        return response;
    }
}
