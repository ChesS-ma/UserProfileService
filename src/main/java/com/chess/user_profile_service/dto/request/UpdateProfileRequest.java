package com.chess.user_profile_service.dto.request;

import com.chess.user_profile_service.models.ChessPreferences;
import com.chess.user_profile_service.models.UserProfile;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProfileRequest {

    private String username ;
    private String bio ;

}
