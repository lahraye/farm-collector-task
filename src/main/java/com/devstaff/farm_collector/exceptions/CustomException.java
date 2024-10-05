package com.devstaff.farm_collector.exceptions;

import com.devstaff.farm_collector.constants.ResponseCode;
import lombok.Getter;
import lombok.experimental.StandardException;

@Getter
@StandardException
public class CustomException extends RuntimeException{
    public String code;

    public CustomException(String message){
        super(message);
        code = ResponseCode.BAD_REQUEST.getCode();
    }

}
