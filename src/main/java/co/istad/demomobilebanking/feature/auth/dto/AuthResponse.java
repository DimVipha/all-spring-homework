package co.istad.demomobilebanking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
