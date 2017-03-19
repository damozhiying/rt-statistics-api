package com.github.apro.error;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Created by achoudh on 19/03/2017.
 */

public enum Error {
    GENERIC_ERROR(1000),
    ASYNC_ERROR(1001),
    TRANSACTION_ERROR(1002);

    @Getter(onMethod = @__(@JsonValue))
    private int code;

    Error(int code) {
        this.code = code;
    }
}
