package com.chess.user_profile_service.exceptions;

public class ProfileNotFoundException extends RuntimeException{
    public  ProfileNotFoundException(String message) {
        super(message);
    }
    public ProfileNotFoundException(String message, Throwable cause) {}
}
