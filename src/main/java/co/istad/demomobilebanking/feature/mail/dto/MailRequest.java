package co.istad.demomobilebanking.feature.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailRequest(
        @NotBlank(message = "Email is required")
        String to,

        @NotBlank(message = "Subject is required")
        String subject

) {
}
