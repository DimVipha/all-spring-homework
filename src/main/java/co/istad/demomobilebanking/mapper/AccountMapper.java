package co.istad.demomobilebanking.mapper;

import co.istad.demomobilebanking.domain.Account;
import co.istad.demomobilebanking.feature.account.dto.AccountCreateRequest;
import co.istad.demomobilebanking.feature.account.dto.AccountResponse;
import co.istad.demomobilebanking.feature.account.dto.AccountSnippetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "user",
            qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);

    AccountSnippetResponse toAccountSnippetResponse(Account account);

    //@Mapping(source = "userAccountList",target = "user",qualifiedByName ="mapUserResponse" )
    List<AccountResponse> toAccountResponseList(List<Account> account);
}





