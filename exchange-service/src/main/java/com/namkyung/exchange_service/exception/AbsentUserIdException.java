package com.namkyung.exchange_service.exception;

public class AbsentUserIdException extends RuntimeException {
    public AbsentUserIdException(String message) {
        super(message);
    }
}
