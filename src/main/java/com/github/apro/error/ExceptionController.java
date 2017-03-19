package com.github.apro.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(TransactionCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError processTransactionError(TransactionCreationException ex) {
        log.error(ex.getMessage());

        final ApiError error = new ApiError(ex.getCode(), ex.getMessage());
        log.error(error.toString());
        return error;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiError processThrowable(Throwable ex) {
        log.error(ex.getMessage());

        final ApiError error = new ApiError(Error.GENERIC_ERROR, ex.getMessage());
        log.error(error.toString());
        return error;
    }
}