package com.namkyung.exchange_service.exception;

public class DuplicateUserIdException extends RuntimeException{

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
