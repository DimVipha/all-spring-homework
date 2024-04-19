package co.istad.demomobilebanking.mapper;
import co.istad.demomobilebanking.domain.AccountType;
import co.istad.demomobilebanking.feature.accountType.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    List<AccountTypeResponse> toListAccountTypes(List<AccountType> accountTypes);

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);

}
