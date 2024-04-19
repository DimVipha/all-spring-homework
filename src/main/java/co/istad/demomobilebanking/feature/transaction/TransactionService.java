package co.istad.demomobilebanking.feature.transaction;

import co.istad.demomobilebanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);

    Page<TransactionResponse> findList(int page, int size,String sort,String transferType);
}
