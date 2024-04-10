package co.istad.demomobilebanking.feature.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "account is re")
        String ownerActNo,

        @NotBlank(message = "transfer account no of receiver is required")
        String transactionReceiverActNo,

        @NotNull(message = "account is required")
        BigDecimal amount,

        String remark

) {
}
