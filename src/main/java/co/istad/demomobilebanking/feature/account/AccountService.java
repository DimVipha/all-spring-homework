package co.istad.demomobilebanking.feature.account;

import co.istad.demomobilebanking.feature.account.dto.AccountCreateRequest;
import co.istad.demomobilebanking.feature.account.dto.AccountRenameRequest;
import co.istad.demomobilebanking.feature.account.dto.AccountResponse;
import co.istad.demomobilebanking.feature.account.dto.AccountUpdateTransferLimitRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService{
    Page<AccountResponse> findList(int page, int size);

    void hideAccount(String actNo);

    AccountResponse renameByActNo(String actNo,
                                  AccountRenameRequest accountRenameRequest);

    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);
    List<AccountResponse> findAll();
    AccountResponse updateTransferLimit(String actNo, AccountUpdateTransferLimitRequest accountUpdateTransferLimitRequest);

}
