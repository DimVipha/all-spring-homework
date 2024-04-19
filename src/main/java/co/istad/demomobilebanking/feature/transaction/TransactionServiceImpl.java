package co.istad.demomobilebanking.feature.transaction;


import co.istad.demomobilebanking.domain.Account;
import co.istad.demomobilebanking.domain.Transaction;
import co.istad.demomobilebanking.feature.account.AccountRepository;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionCreateRequest;
import co.istad.demomobilebanking.feature.transaction.dto.TransactionResponse;
import co.istad.demomobilebanking.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{

    private  final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private  final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {
        log.info("transfer(TransactionCreateRequest transactionCreateRequest)");

        // validate owner account number
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner has not been found"
                ));

    // validate transfer receiver
        Account transferReceiver=accountRepository.findByActNo(transactionCreateRequest.transactionReceiverActNo())
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,"account number has not been found"
                        )
                );

        // check amount transfer (balance < amount) (act owner only)
        if(owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Insufficient balance"
            );
        }

        // check amount transfer with transfer limit
        if (transactionCreateRequest.amount().doubleValue() >= owner.getTransferLimit().doubleValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit");
        }

        // ដកលុយចេញពីគណនី
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        // បញ្ចូលលុយទៅគណនី
        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);

    }

    @Override
    public Page<TransactionResponse> findList(int page, int size, String sort,String transactionType) {

        if (page<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid page number,Page greater than or equal to zero");
        }
        if (size<1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid page size,Page size greater than or equal to one");
        }
        Sort.Direction direction;
        String property;
        if (sort != null && sort.startsWith("date")) {
            String[] sortParts = sort.split(":");
            if (sortParts.length == 2 && sortParts[1].equalsIgnoreCase("desc")) {
                direction = Sort.Direction.DESC;
            } else {
                direction = Sort.Direction.ASC;
            }
            property = "transactionAt"; // Assuming transaction date property name
        } else {

            direction = Sort.Direction.DESC;
            property = "transactionAt";
        }
        Sort sortByTransactionDate = Sort.by(direction, property);
        PageRequest pageRequest = PageRequest.of(page, size, sortByTransactionDate);
        Page<Transaction> transactions;
        transactionType = transactionType.toUpperCase();
        if (transactionType.equals("TRANSFER")){
            transactions= transactionRepository.findByTransactionType(transactionType,pageRequest);
        }
        else if (transactionType.equals("PAYMENT")){
            transactions= transactionRepository.findByTransactionType(transactionType,pageRequest);
        }else {
            transactions= transactionRepository.findAll(pageRequest);
        }
        return transactions.map(transactionMapper::toTransactionResponse);
    }

}
