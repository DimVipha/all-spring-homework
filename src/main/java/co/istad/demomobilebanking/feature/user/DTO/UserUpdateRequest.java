package co.istad.demomobilebanking.feature.user.DTO;

import java.time.LocalDate;

public record UserUpdateRequest(
        String name,
        String gender,
        LocalDate dob,
        String studentIdCard
) {
}
