package co.istad.demomobilebanking.feature.user.DTO;

import java.time.LocalDate;
import java.util.List;

public record UserDetailResponse(
        String uuid,
        String name,
        String profileImage,
        String gender,
        LocalDate dob,
        String cityOrProvince,
        String Street,
        String position,
        String monthlyIncomeRang,
        String StudentIdCard,
        String employeeType,
        List<RoleNameResponse> roles
) {

}
