package co.istad.demomobilebanking.feature.accountType;

import co.istad.demomobilebanking.domain.AccountType;
import co.istad.demomobilebanking.feature.accountType.dto.AccountTypeResponse;
import co.istad.demomobilebanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeResponse> findAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypeMapper.toListAccountTypes(accountTypes);
    }

    @Override
    public AccountTypeResponse findAccountTypeByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAliasIgnoreCase(alias).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account does not exits with "+ alias +" alias"
                )
        );
        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
