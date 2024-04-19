package co.istad.demomobilebanking.feature.accountType;

import co.istad.demomobilebanking.feature.accountType.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accountTypes")
@RequiredArgsConstructor

public class AccountTypeController {
    private final AccountTypeService accountTypeService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AccountTypeResponse> findAllAccountTypes(){
        return accountTypeService.findAllAccountTypes();
    }

    @GetMapping("/{alias}")
    @ResponseStatus(HttpStatus.OK)
    AccountTypeResponse findAccountTypeByAlias(@PathVariable String alias){
        return accountTypeService.findAccountTypeByAlias(alias);
    }
}