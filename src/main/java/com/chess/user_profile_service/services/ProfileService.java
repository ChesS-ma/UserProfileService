package com.chess.user_profile_service.services;

import com.chess.user_profile_service.dto.request.UpdateChessPreferencesRequest;
import com.chess.user_profile_service.models.ChessPreferences;
import com.chess.user_profile_service.models.UserProfile;
import com.chess.user_profile_service.dto.request.UpdateProfileRequest ;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    UserProfile createProfile(String username , UUID user_id  , Integer birthYear );

    Optional<UserProfile> findProfileById(UUID user_id) ;

    UserProfile updateProfile(UUID userId, UpdateProfileRequest request);

    UserProfile updateOnlineStatus(UUID userId , boolean isOnline );

    void deleteProfile(UUID userId) ;

    ////////// ChessPreferences Operations //////////

    ChessPreferences updateChessPreferences(UUID userId , UpdateChessPreferencesRequest request ) ;

    ChessPreferences getChessPreferences(UUID userId) ;

}
