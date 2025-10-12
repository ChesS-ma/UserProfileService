package com.chess.user_profile_service.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ChessPreferences {
    @Id
    @Column(name = "pref_id")
    private UUID user_id ;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserProfile user;

    @Column(name = "board_theme")
    private String BoardTheme = "Classic";

    @Column(name = "piece_set")
    private String pieceSet = "classic";

    @Column(name="sound_enabled")
    private boolean soundEnabled = true;

    @Column(name = "show_cordinates")
    private boolean showCordinates = true;

    @Column(name = "auto_queen")
    private boolean autoQueen = true;

    @Column(name = "premove_enabled")
    private boolean premoveEnabled = false;
}
