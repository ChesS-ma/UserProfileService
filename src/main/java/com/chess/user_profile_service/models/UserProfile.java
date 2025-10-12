package com.chess.user_profile_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID ;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {

    public enum MembershipType{
        BASIC,
        PREMIUM,
        DIAMOND
    }
    @Id
    @Column(updatable = false )
    private UUID id ;

    @Column(unique = true , nullable = false)
    private String username ;

    @Column(length = 300 , columnDefinition = "TEXT")
    private String bio ;

    @Column(name = "country_code", length = 2)
    private String countryCode; // For flag

    @Column(name = "is_online")
    private boolean isOnline = false ;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt ;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt ;

    @Column(name = "birthday")
    private Integer birthDay ;


    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type" , nullable = false)
    private MembershipType membershipType = MembershipType.BASIC ;
    
    @OneToOne(mappedBy = "userProfile" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private ChessPreferences chessPreferences  ;



}
