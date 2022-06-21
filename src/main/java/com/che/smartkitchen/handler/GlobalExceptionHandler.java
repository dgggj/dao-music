package com.che.smartkitchen.handler;

import com.che.smartkitchen.exception.BizException;
import com.che.smartkitchen.exception.ErrorResponse;
import com.che.smartkitchen.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BizException.class)
    public ErrorResponse bizExceptionHandler(BizException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setCode(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTrace(e.getStackTrace());
        return errorResponse;
    }
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse exceptionHandler(Exception e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
        errorResponse.setMessage(ExceptionType.INNER_ERROR.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setCode(ExceptionType.FORBIDDEN.getCode());
        errorResponse.setMessage(ExceptionType.FORBIDDEN.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse bizExceptionHandler(MethodArgumentNotValidException e){
        ErrorResponse errorResponse=new ErrorResponse();
        e.getBindingResult().getAllErrors().forEach((error)->{
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(ExceptionType.BAD_REQUEST.getMessage());
        });
        return errorResponse;

    }

}
