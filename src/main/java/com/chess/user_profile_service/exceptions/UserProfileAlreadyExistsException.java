package com.chess.user_profile_service.exceptions;

public class UserProfileAlreadyExistsException extends RuntimeException {

    public UserProfileAlreadyExistsException(String message) {
        super(message);
    }

    public UserProfileAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
