package com.ctytech.gameszone.api.responses;

import java.util.Objects;

public record AuthResponse(String userName, String accessToken) {

    public AuthResponse {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(accessToken);
    }
}
