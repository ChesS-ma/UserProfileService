package com.chess.user_profile_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateOnlineStatusRequest {
    @JsonProperty("isOnline")
    private boolean isOnline;
}