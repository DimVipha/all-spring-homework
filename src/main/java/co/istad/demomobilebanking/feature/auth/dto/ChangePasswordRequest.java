package co.istad.demomobilebanking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "old password is require")
        String oldPassword,

        @NotBlank(message = " password is require")
        String password,

        @NotBlank(message = "confirm password is require")
        String confirmPassword
) {
}
