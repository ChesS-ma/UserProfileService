package com.chess.user_profile_service.dto.response;

import com.chess.user_profile_service.models.UserProfile;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;
@Builder
@Data
public class UserProfileResponse {

    private UUID userId ;

    private String username ;

    private String bio ;

    private String countryCode; // For flag

    private String  membershipType ;

    private boolean isOnline ;

    private Instant createdAt ;

    private Instant updatedAt ;

    private Integer birthYear ;

    public static UserProfileResponse fromEntity(UserProfile profile ){
        return UserProfileResponse.builder()
                .userId(profile.getId())
                .username(profile.getUsername())
                .bio(profile.getBio())
                .countryCode(profile.getCountryCode())
                .birthYear(profile.getBirthYear())
                .membershipType(profile.getMembershipType().name())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .isOnline(profile.isOnline())
                .build();
    }
}
