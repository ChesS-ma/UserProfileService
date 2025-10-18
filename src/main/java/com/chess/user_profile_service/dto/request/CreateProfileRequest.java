package com.chess.user_profile_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;
@Data
public class CreateProfileRequest {

    @NotNull // The userId cannot be null
    private UUID userId;


    @NotBlank // The username cannot be null or an empty string
    @Size(min = 3, max = 20) // The length must be between 3 and 20 characters
    private String username;

    @Min(1900) // The birth year must be at least 1900
    private Integer birthYear;
}
