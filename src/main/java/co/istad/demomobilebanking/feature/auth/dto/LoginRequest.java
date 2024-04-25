package co.istad.demomobilebanking.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;

public record LoginRequest(
        @NotBlank
        String phoneNumber,

        @NotBlank
        String password
) {
}
