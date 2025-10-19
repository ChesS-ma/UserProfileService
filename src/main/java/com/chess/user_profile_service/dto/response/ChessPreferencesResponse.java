package com.chess.user_profile_service.dto.response;

import com.chess.user_profile_service.models.ChessPreferences;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChessPreferencesResponse {

    private UUID userId;
    private String boardTheme;
    private String pieceSet;
    private boolean soundEnabled;
    private boolean showCoordinates;
    private boolean autoQueen;
    private boolean premoveEnabled;

    public static ChessPreferencesResponse fromEntity(ChessPreferences preferences) {
        return ChessPreferencesResponse.builder()
                .userId(preferences.getUserId())
                .boardTheme(preferences.getBoardTheme())
                .pieceSet(preferences.getPieceSet())
                .soundEnabled(preferences.isSoundEnabled())
                .showCoordinates(preferences.isShowCoordinates())
                .autoQueen(preferences.isAutoQueen())
                .premoveEnabled(preferences.isPremoveEnabled())
                .build();
    }
}