package com.chess.user_profile_service.services;

import com.chess.user_profile_service.models.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileService {

    Optional<UserProfile> createProfile(String username , UUID user_id  , Integer birthYear );

    void updateProfile(UserProfile userProfile) ;

    void deleteProfile(UserProfile userProfile) ;

    Optional<UserProfile> findProfile(UUID id );
}
