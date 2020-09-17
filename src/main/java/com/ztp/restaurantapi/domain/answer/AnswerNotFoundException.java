package com.ztp.restaurantapi.domain.answer;

import com.ztp.restaurantapi.exception.DomainException;
import com.ztp.restaurantapi.exception.ExceptionMessage;
import com.ztp.restaurantapi.message.ResponseCode;

@ExceptionMessage(responseCode = ResponseCode.ANSWER_NOT_FOUND)
public class AnswerNotFoundException extends DomainException {
}
