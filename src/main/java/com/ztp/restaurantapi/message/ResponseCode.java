package com.ztp.restaurantapi.message;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(100, "SUCCESS"),
    REQUEST_HAS_BEEN_SENT(101, "REQUEST HAS BEEN SENT"),
    BAD_REQUEST(120, "BAD REQUEST"),

    // System Error
    UNAUTHORIZED_PROCESS(130, "UNAUTHORIZED PROCESS"),
    ACCESS_DENIED(131, "ACCESS DENIED"),
    INVALID_REQUEST(132, "INVALID REQUEST"),

    USER_NOT_FOUND(250, "USER NOT FOUND"),
    RESTAURANT_NOT_FOUND(251, "RESTAURANT NOT FOUND"),
    REVIEW_NOT_FOUND(252, "REVIEW NOT FOUND"),
    ANSWER_NOT_FOUND(253, "ANSWER NOT FOUND"),


    MAX_ANSWER_LIMIT(301,"YOU'VE REACHED THE MAXIMUM ANSWER LIMIT (1)"),

    UNDEFINED_ERROR(999, "UNDEFINED ERROR OCCURRED");

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
