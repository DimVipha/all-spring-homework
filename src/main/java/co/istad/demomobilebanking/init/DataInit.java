package co.istad.demomobilebanking.init;

import co.istad.demomobilebanking.domain.AccountType;
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
    void init() {

        // Auto generate role (USER, CUSTOMER, STAFF, ADMIN)
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }


    }
    @PostConstruct
    void initAccountType(){
        if (accountTypeRepository.count() < 1){


            AccountType payRoll = new AccountType();
            payRoll.setName("Payroll account");
            payRoll.setAlias("payroll");
            payRoll.setIsDeleted(false);
            payRoll.setDescription("For payroll account type");

            //saving account
            AccountType saving = new AccountType();
            saving.setName("Saving");
            saving.setAlias("saving");
            saving.setIsDeleted(false);
            saving.setDescription("For saving account type");

            accountTypeRepository.saveAll(
                    List.of(payRoll,saving)
            );


        }

    }

    @PostConstruct
    void initCardType(){
        if (cardTypeRepository.count()<1){
            CardType visaCard = new CardType();
            visaCard.setName("Visa Card");
            visaCard.setIsDeleted(false);

            CardType masterCard = new CardType();
            masterCard.setName("MasterCard");
            masterCard.setIsDeleted(false);

            cardTypeRepository.saveAll(
                    List.of(visaCard,masterCard)
            );

        }

    }
}


