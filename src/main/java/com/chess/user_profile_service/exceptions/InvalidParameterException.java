package com.chess.user_profile_service.exceptions;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message);
    }
}