package com.github.apro.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError processTransactionError(TransactionCreationException ex) {
        log.error(ex.getMessage());

        final ApiError error = new ApiError(ex.getCode(), ExceptionUtils.getRootCauseMessage(ex));
        log.error(error.toString());
        return error;
    }

    @ExceptionHandler(value = {Throwable.class,})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError processThrowable(Throwable ex) {
        log.error(ex.getMessage());
        return new ApiError(Error.GENERIC_ERROR, ExceptionUtils.getRootCauseMessage(ex));
    }
}