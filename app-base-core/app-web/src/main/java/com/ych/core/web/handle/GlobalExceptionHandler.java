package com.ych.core.web.handle;

import com.ych.core.web.entity.Response;
import com.ych.core.web.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException e) {
        return Response.fail(e.getResponseCode());
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return Response.fail(e.getMessage());
    }

}
