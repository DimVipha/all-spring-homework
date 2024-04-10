package co.istad.demomobilebanking.feature.account.dto;


import co.istad.demomobilebanking.feature.accountType.dto.AccountTypeResponse;
import co.istad.demomobilebanking.feature.user.DTO.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountType,
        UserResponse user

) {
}