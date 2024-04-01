package co.istad.demomobilebanking.feature.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank
        String name
) {
}
