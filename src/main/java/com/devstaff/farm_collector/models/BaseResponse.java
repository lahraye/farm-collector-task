package com.devstaff.farm_collector.models;

import com.devstaff.farm_collector.constants.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Builder
@Slf4j
@Data
public class BaseResponse {
    protected String responseMessage;
    protected String responseCode = "00";


    public BaseResponse(String code, String message){
        this.responseCode = code;
        this.responseMessage = message;
    }

    public BaseResponse(ResponseCode responseCode) {
        this.setResponseCode(responseCode.getCode());
        this.setResponseMessage(responseCode.getMessage());
    }
}
