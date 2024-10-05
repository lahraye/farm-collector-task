package com.devstaff.farm_collector.exceptions;

import com.devstaff.farm_collector.constants.ResponseCode;
import lombok.Getter;

public class NotFoundException extends CustomException{
    public NotFoundException(String message){
        super(message);
        code = ResponseCode.NOT_FOUND.getCode();
    }

    public NotFoundException(){
        this(ResponseCode.NOT_FOUND.getMessage());
    }
}
