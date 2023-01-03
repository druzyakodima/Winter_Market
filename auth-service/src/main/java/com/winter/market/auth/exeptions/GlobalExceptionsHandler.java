package com.winter.market.auth.exeptions;

import com.winter.market.api.dtos.AppError;
import com.winter.market.api.dtos.NotFoundExciton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> handlerNotFoundException(NotFoundExciton e) {
        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
