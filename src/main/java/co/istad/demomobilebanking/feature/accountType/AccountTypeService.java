package co.istad.demomobilebanking.feature.accountType;

import co.istad.demomobilebanking.feature.accountType.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findAllAccountTypes();
    AccountTypeResponse findAccountTypeByAlias(String alias);
}
