package com.chess.user_profile_service.repositories;

import com.chess.user_profile_service.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile , UUID> {

    Optional<UserProfile> findUserProfileByUsername(String username);

    boolean existsByUsername(String username);
}
