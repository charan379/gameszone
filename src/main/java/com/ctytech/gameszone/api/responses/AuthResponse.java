package com.ctytech.gameszone.api.responses;

import java.util.List;
import java.util.Objects;

import com.ctytech.gameszone.constants.UserStatus;

public record AuthResponse(String userName, String accessToken, List<String> roles, UserStatus status) {

    public AuthResponse {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(accessToken);
        Objects.requireNonNull(roles);
        Objects.requireNonNull(status);
    }
}
