package co.istad.demomobilebanking.feature.transaction;

import co.istad.demomobilebanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest) {
        return transactionService.transfer(transactionCreateRequest);
    }

    @GetMapping
    Page<TransactionResponse> findList(@RequestParam(required = false , defaultValue ="0") int page,
                                       @RequestParam(required = false,defaultValue = "25" )int size,
                                       @RequestParam(required = false,defaultValue = "desc") String sort,
                                       @RequestParam(required = false,defaultValue = "") String transactionType){

        return transactionService.findList(page,size,sort,transactionType);

    }

}

