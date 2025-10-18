package com.chess.user_profile_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChessPreferences {
    @Id
    private UUID userId ;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserProfile userProfile ;

    @Column(name = "board_theme")
    private String boardTheme = "Classic";

    @Column(name = "piece_set")
    private String pieceSet = "classic";

    @Column(name="sound_enabled")
    private boolean soundEnabled = true;

    @Column(name = "show_cordinates")
    private boolean showCoordinates = true;

    @Column(name = "auto_queen")
    private boolean autoQueen = false;

    @Column(name = "premove_enabled")
    private boolean premoveEnabled = false;
}
