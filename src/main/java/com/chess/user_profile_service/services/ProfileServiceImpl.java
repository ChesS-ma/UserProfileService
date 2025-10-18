package com.chess.user_profile_service.services;

import com.chess.user_profile_service.dto.request.UpdateChessPreferencesRequest;
import com.chess.user_profile_service.dto.request.UpdateProfileRequest;
import com.chess.user_profile_service.exceptions.InvalidParameterException;
import com.chess.user_profile_service.exceptions.ProfileNotFoundException;
import com.chess.user_profile_service.exceptions.UserProfileAlreadyExistsException;
import com.chess.user_profile_service.models.ChessPreferences;
import com.chess.user_profile_service.models.UserProfile;
import com.chess.user_profile_service.repositories.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserProfileRepository userProfileRepository;

    private void validateParameters(String username , UUID user_id ,Integer birthYear) {
        // Check userId is not null
        if (user_id == null) {
            throw new InvalidParameterException("User ID cannot be null");
        }

        // Check username
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidParameterException("Username cannot be empty");
        }

        if (username.length() < 3) {
            throw new InvalidParameterException("Username must be at least 3 characters");
        }

        if (username.length() > 50) {
            throw new InvalidParameterException("Username must be less than 50 characters");
        }

        // Check username format (only letters, numbers, underscore)
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new InvalidParameterException("Username can only contain letters, numbers, and underscores");
        }

        // Check birthYear (optional but if provided, must be valid)
        if (birthYear != null) {
            int currentYear = java.time.Year.now().getValue();
            if (birthYear < 1900 || birthYear > currentYear) {
                throw new InvalidParameterException("Invalid birth year: " + birthYear);
            }
        }
    }

    @Override
    @Transactional
    public UserProfile createProfile(String username, UUID user_id, Integer birthYear) {

        validateParameters(username , user_id , birthYear) ;

        if (userProfileRepository.existsByUsername(username)){
            log.warn("Username already exists");
            throw new UserProfileAlreadyExistsException("Sorry, that username "+username+" is already taken by someone else ..") ;
        }

        // Create user profile
        UserProfile profile = UserProfile.builder()
                .id(user_id)
                .username(username)
                .birthYear(birthYear)
                .membershipType(UserProfile.MembershipType.BASIC)
                .isOnline(false)
                .build();

        ChessPreferences preferences = ChessPreferences.builder()
                .userProfile(profile)
                .boardTheme("classic")
                .pieceSet("classic")
                .soundEnabled(true)
                .showCoordinates(true)
                .autoQueen(false)
                .premoveEnabled(false)
                .build();

        // Link preferences to profile
        profile.setChessPreferences(preferences);

        UserProfile savedProfile = userProfileRepository.save(profile);
        log.info("Profile created successfully for user: {}", user_id);

        return savedProfile;
    }

    @Override
    public Optional<UserProfile> findProfileById(UUID user_id)
    {
        return  userProfileRepository.findById(user_id) ;
    }

    @Override
    @Transactional
    public UserProfile updateProfile(UUID userId, UpdateProfileRequest request) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("the user with the id "+userId.toString()+" does not Exist ... ") ) ;
        if(request.getBio() != null ){
            userProfile.setBio(request.getBio());
        }
        if(request.getUsername() != null){
            userProfile.setUsername(request.getUsername());
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile updateOnlineStatus(UUID userId, boolean isOnline) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("the user with the id "+userId.toString()+" does not Exist ... ") ) ;
        userProfile.setOnline(isOnline);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteProfile(UUID userId) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("the user with the id "+userId.toString()+" does not Exist ... ") ) ;
        userProfileRepository.delete(userProfile);
    }

    /// the Chess Preferences Operations ///


    @Override
    public ChessPreferences updateChessPreferences(UUID userId, UpdateChessPreferencesRequest request) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("the user with the id "+userId.toString()+" does not Exist ... ") ) ;

        ChessPreferences preferences = userProfile.getChessPreferences();
        if(request.getBoardTheme()!=null){
            preferences.setBoardTheme(request.getBoardTheme());
        }
        if(request.getPieceSet()!=null){
            preferences.setPieceSet(request.getPieceSet());
        }
        if(request.isAutoQueen() != preferences.isAutoQueen()) {
            preferences.setAutoQueen(request.isAutoQueen());
        }

        if(request.isPremoveEnabled() != preferences.isPremoveEnabled()) {
            preferences.setPremoveEnabled(request.isPremoveEnabled());
        }

        if(request.isShowCoordinates() != preferences.isShowCoordinates()) {
            preferences.setShowCoordinates(request.isShowCoordinates());
        }

        if(request.isSoundEnabled() != preferences.isSoundEnabled()) {
            preferences.setSoundEnabled(request.isSoundEnabled());
        }

        userProfileRepository.save(userProfile);
        return preferences ;
    }

    @Override
    public ChessPreferences getChessPreferences(UUID userId) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("the user with the id "+userId.toString()+" does not Exist ... ") ) ;
        return userProfile.getChessPreferences() ;
    }
}
