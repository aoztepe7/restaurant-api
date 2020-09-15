package com.ztp.restaurantapi.domain.user;

import com.ztp.restaurantapi.exception.DomainException;
import com.ztp.restaurantapi.exception.ExceptionMessage;
import com.ztp.restaurantapi.message.ResponseCode;

@ExceptionMessage(responseCode = ResponseCode.USER_NOT_FOUND)
public class UserNotFoundException extends DomainException {
}
