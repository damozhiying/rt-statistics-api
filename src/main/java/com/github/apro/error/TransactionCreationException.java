package com.github.apro.error;

import lombok.Getter;

@Getter
public class TransactionCreationException extends RuntimeException {

    private static final long serialVersionUID = -4721021569957101020L;
    private Error code;

    public TransactionCreationException(Error code, String msg) {
        super(msg);
        this.code = code;
    }
}
