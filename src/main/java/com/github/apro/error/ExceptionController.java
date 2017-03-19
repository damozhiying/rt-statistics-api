package com.github.apro.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError processThrowable(Throwable ex) {
        log.error(ex.getMessage());
        return new ApiError(Error.GENERIC_ERROR, "Internal server error");
    }
}