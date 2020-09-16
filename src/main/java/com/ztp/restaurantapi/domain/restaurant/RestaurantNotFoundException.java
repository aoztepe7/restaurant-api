package com.ztp.restaurantapi.domain.restaurant;

import com.ztp.restaurantapi.exception.DomainException;
import com.ztp.restaurantapi.exception.ExceptionMessage;
import com.ztp.restaurantapi.message.ResponseCode;

@ExceptionMessage(responseCode = ResponseCode.RESTAURANT_NOT_FOUND)
public class RestaurantNotFoundException extends DomainException {
}
