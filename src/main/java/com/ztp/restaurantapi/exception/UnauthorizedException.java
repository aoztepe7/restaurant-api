package com.ztp.restaurantapi.exception;

import com.ztp.restaurantapi.message.ResponseCode;

@ExceptionMessage(responseCode = ResponseCode.UNAUTHORIZED_PROCESS)
public class UnauthorizedException extends RuntimeException {
}
