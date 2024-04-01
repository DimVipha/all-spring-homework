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
            payRoll.setAlias("payroll account type");
            payRoll.setIsDeleted(false);
            payRoll.setDescription("a type of account used specifically for employee compensation, whether it's to do with salary, wage, or bonuses");
            accountTypeRepository.save(payRoll);

            //saving account type
            AccountType savingAct = new AccountType();
            savingAct.setName("Saving");
            savingAct.setAlias("saving");
            savingAct.setIsDeleted(false);
            savingAct.setDescription(" a deposit account held at a financial institution that provides security for your principal and a modest interest rate");
            accountTypeRepository.save(savingAct);


            AccountType cardAccount=new AccountType();
            cardAccount.setName("card account ");
            cardAccount.setAlias("saving account type");
            cardAccount.setIsDeleted(false);
            cardAccount.setDescription("Card Account means the Cardholder's Account(s) with the Bank in respect of which the Card is issued,");
            accountTypeRepository.save(cardAccount);

             /*accountTypeRepository.saveAll(
                    List.of(payRoll,saving)
            );*/

        }

    }

    @PostConstruct
    void initCardType(){
        if (cardTypeRepository.count()<1){
            CardType visaCard = new CardType();
            visaCard.setName("Visa Card");
            visaCard.setIsDeleted(false);
            cardTypeRepository.save(visaCard);

            CardType masterCard = new CardType();
            masterCard.setName("MasterCard");
            masterCard.setIsDeleted(false);
            cardTypeRepository.save(masterCard);

            /*cardTypeRepository.saveAll(
                    List.of(visaCard,masterCard)
            );*/

        }

    }
}


