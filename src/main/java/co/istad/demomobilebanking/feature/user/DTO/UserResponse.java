package co.istad.demomobilebanking.feature.user.DTO;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String name,
        String profileImage,
        String gender,
        LocalDate dob
) {
}
