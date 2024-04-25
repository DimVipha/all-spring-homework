package co.istad.demomobilebanking.init;

import co.istad.demomobilebanking.domain.AccountType;
import co.istad.demomobilebanking.domain.Authority;
import co.istad.demomobilebanking.domain.CardType;
import co.istad.demomobilebanking.domain.Role;
import co.istad.demomobilebanking.feature.accountType.AccountTypeRepository;
import co.istad.demomobilebanking.feature.cardtype.CardTypeRepository;
import co.istad.demomobilebanking.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CardTypeRepository cardTypeRepository;
    @PostConstruct
    void initRole(){
        // auto generated
        if (roleRepository.count()<1){
            Authority userRead = new Authority();
            userRead.setName("user:read");
            Authority userWrite = new Authority();
            userWrite.setName("user:write");
            Authority transactionRead = new Authority();
            transactionRead.setName("transaction:read");
            Authority transactionWrite = new Authority();
            transactionWrite.setName("transaction:write");
            Authority accountRead = new Authority();
            accountRead.setName("account:read");
            Authority accountWrite = new Authority();
            accountWrite.setName("account:write");
            Authority accountTypeRead = new Authority();
            accountTypeRead.setName("account:read");
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setName("account:write");

            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(List.of(
                    userRead,transactionRead,accountRead,accountTypeRead
            ));

            Role customer = new Role();
            customer.setName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite,transactionWrite,accountWrite
            ));

            Role staff = new Role();
            staff.setName("STAFF");
            staff.setAuthorities(List.of(
                    accountTypeWrite
            ));

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite,accountWrite,accountTypeWrite
            ));

            roleRepository.saveAll(
                    List.of(user,customer,staff,admin)
            );
        }

    }

    @PostConstruct
    void initAccountType(){
        if (accountTypeRepository.count()<1){
            AccountType payRoll = new AccountType();
            payRoll.setName("Payroll Account");
            payRoll.setAlias("payroll-account");
            payRoll.setIsDeleted(false);
            payRoll.setDescription("A payroll account is a checking account that a business uses to pay its employees.");

            AccountType saving = new AccountType();
            saving.setName("Saving Account");
            saving.setAlias("saving-account");
            saving.setIsDeleted(false);
            saving.setDescription("A savings account is a type of bank account that allows you to store money and earn interest.");

            AccountType card = new AccountType();
            card.setName("Card Account");
            card.setAlias("card-account");
            card.setIsDeleted(false);
            card.setDescription("A card account is the account that a cardholder has with a bank, and where withdrawals are debited and deposits are credited when the cardholder makes a transaction. ");
            accountTypeRepository.save(card);

            accountTypeRepository.saveAll(
                    List.of(payRoll,saving)
            );


        }

    }

    @PostConstruct
    void initCardType(){
        // auto generated
        if (cardTypeRepository.count()<1){
            CardType visa = new CardType();
            visa.setName("Visa");
            visa.setIsDeleted(false);

            CardType masterCard = new CardType();
            masterCard.setName("MasterCard");
            visa.setIsDeleted(false);

            cardTypeRepository.saveAll(
                    List.of(visa,masterCard)
            );

        }

    }
}


