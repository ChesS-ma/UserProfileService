package com.chess.user_profile_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chess.user_profile_service.models.ChessPreferences ;


import java.util.UUID;

public interface ChessPreferencesRepository extends JpaRepository<ChessPreferences , UUID> {
}
