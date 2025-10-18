package com.chess.user_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChessPreferencesRequest {

    private String boardTheme;
    private String pieceSet;
    private boolean soundEnabled;
    private boolean showCoordinates;
    private boolean autoQueen;
    private boolean premoveEnabled;

}