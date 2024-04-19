package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.Transaction;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);

}
