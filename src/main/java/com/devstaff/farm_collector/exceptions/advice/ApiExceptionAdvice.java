package com.devstaff.farm_collector.exceptions.advice;

import com.devstaff.farm_collector.constants.ResponseCode;
import com.devstaff.farm_collector.exceptions.CustomException;
import com.devstaff.farm_collector.exceptions.NotFoundException;
import com.devstaff.farm_collector.models.BaseError;
import com.devstaff.farm_collector.models.BaseResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleNotFoundException(NotFoundException e) {

        String errorMessage = e.getMessage();
        errorMessage =
                StringUtils.isBlank(e.getMessage()) ? ResponseCode.NOT_FOUND.getMessage() : errorMessage;

        return new BaseResponse(e.getCode(), errorMessage);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMoniepointException(CustomException e) {
        String errorMessage = e.getMessage();
        errorMessage =
                StringUtils.isBlank(e.getMessage()) ? ResponseCode.NOT_FOUND.getMessage() : errorMessage;

        return new BaseResponse(e.getCode(), errorMessage);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {

        var response = new BaseResponse(ResponseCode.BAD_REQUEST);
        if (StringUtils.isNotBlank(e.getParameterName())) {
            response.setResponseMessage(String.format("%s is a required", e.getParameterName()));
        }
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new BaseResponse(ResponseCode.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException e) {
        return new BaseResponse(ResponseCode.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        return new BaseResponse(ResponseCode.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleValidationException(ValidationException e) {
        return new BaseResponse(ResponseCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception e) {
        log.error("handling unexpected error: ", e);
        var response = new BaseResponse();
        response.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleConstraintViolationException(ConstraintViolationException e) {
        var response = new BaseResponse(ResponseCode.BAD_REQUEST);
        String responseMessage = ResponseCode.BAD_REQUEST.getMessage();
        Optional<ConstraintViolation<?>> constraintViolationOptional =
                e.getConstraintViolations().stream().findFirst();
        if (constraintViolationOptional.isPresent()) {
            responseMessage = constraintViolationOptional.get().getMessageTemplate();
        }
        response.setResponseMessage(responseMessage);
        return response;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleValidationException(MethodArgumentNotValidException e) {
        return getBindingExceptions(e.getBindingResult());
    }

    private BaseResponse getBindingExceptions(BindingResult result) {
        var response = new BaseResponse(ResponseCode.BAD_REQUEST);
        List<FieldError> errorList = result.getFieldErrors();
        List<BaseError> errors = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            errors.add(
                    new BaseError(
                            fieldError.getField(),
                            fieldError.isBindingFailure()
                                    ? "Invalid data"
                                    : fieldError.getDefaultMessage()));
        }

        if (!errors.isEmpty()) {
            var responseMessage =
                    String.format(
                            String.format("%s: %s", errors.get(0).getFieldName(), errors.get(0).getMessage()));
            response.setResponseMessage(responseMessage);
        }

        return response;
    }
}
