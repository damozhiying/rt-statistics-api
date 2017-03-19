package com.github.apro.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by achoudh on 19/03/2017.
 */

@Getter
@RequiredArgsConstructor
class ApiError {
    private final Error code;
    private final String message;

    @Override
    public String toString() {
        return code.getCode() + " : " + message;
    }
}
