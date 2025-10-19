package com.chess.user_profile_service.controllers;

import com.chess.user_profile_service.dto.request.CreateProfileRequest;
import com.chess.user_profile_service.dto.request.UpdateChessPreferencesRequest;
import com.chess.user_profile_service.dto.request.UpdateOnlineStatusRequest;
import com.chess.user_profile_service.dto.request.UpdateProfileRequest;
import com.chess.user_profile_service.dto.response.ChessPreferencesResponse;
import com.chess.user_profile_service.dto.response.UserProfileResponse;
import com.chess.user_profile_service.exceptions.ProfileNotFoundException;
import com.chess.user_profile_service.models.ChessPreferences;
import com.chess.user_profile_service.models.UserProfile;
import com.chess.user_profile_service.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<UserProfileResponse> createUserProfile(@Valid @RequestBody CreateProfileRequest request ){
        UserProfile profile = profileService.createProfile(
                request.getUsername() ,
                request.getUserId() ,
                request.getBirthYear()
        ) ;

        return  ResponseEntity.status(HttpStatus.CREATED).body(UserProfileResponse.fromEntity(profile));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable("userId") UUID id ){
        UserProfile profile = profileService.findProfileById(id).orElseThrow(()-> new ProfileNotFoundException("profile is not exist ... ")) ;

        return  ResponseEntity.ok(UserProfileResponse.fromEntity(profile));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@PathVariable("userId") UUID id  , @Valid @RequestBody UpdateProfileRequest request ){
        UserProfile profile = profileService.updateProfile(id , request) ;
        return ResponseEntity.status(HttpStatus.OK).body(UserProfileResponse.fromEntity(profile));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable("userId") UUID  id ){
        profileService.deleteProfile(id) ;
        return ResponseEntity.noContent().build() ;
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserProfileResponse> updateOnlineStatus(@PathVariable("userId") UUID id , @Valid @RequestBody UpdateOnlineStatusRequest request){
        UserProfile profile = profileService.updateOnlineStatus(id, request.isOnline()) ;
        return ResponseEntity.status(HttpStatus.OK).body(UserProfileResponse.fromEntity(profile)) ;
    }


    ///  ChessPreferences Operations ///

    @PutMapping("{userId}/preferences")
    public ResponseEntity<ChessPreferencesResponse> updatePreferences(@PathVariable("userId") UUID id , @Valid @RequestBody UpdateChessPreferencesRequest request ){
        ChessPreferences preferences =  profileService.updateChessPreferences(id, request) ;

        return ResponseEntity.status(HttpStatus.OK).body(ChessPreferencesResponse.fromEntity(preferences)) ;
    }

    @GetMapping("/{userId}/preferences")
    public ResponseEntity<ChessPreferencesResponse> getChessPreferences(@PathVariable UUID userId) {
        ChessPreferences preferences = profileService.getChessPreferences(userId);

        return ResponseEntity.ok(ChessPreferencesResponse.fromEntity(preferences));
    }





}
