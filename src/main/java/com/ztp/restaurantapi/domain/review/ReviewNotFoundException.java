package com.ztp.restaurantapi.domain.review;

import com.ztp.restaurantapi.exception.DomainException;
import com.ztp.restaurantapi.exception.ExceptionMessage;
import com.ztp.restaurantapi.message.ResponseCode;

@ExceptionMessage(responseCode = ResponseCode.REVIEW_NOT_FOUND)
public class ReviewNotFoundException extends DomainException {
}
