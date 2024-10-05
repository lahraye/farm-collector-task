package com.devstaff.farm_collector.constants;

import lombok.Getter;

@Getter
public enum ResponseCode {

    NOT_FOUND("404000","Not Found"),
    BAD_REQUEST("400000", "Bad Request"),
    INTERNAL_SERVER_ERROR(
            "500000",
            "An unexpected error occurred while processing your request. Please try again later.");


    String code;
    String message;
    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
