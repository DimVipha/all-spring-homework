package co.istad.demomobilebanking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

import java.awt.image.ImageProducer;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true, nullable = false, length = 9)
    private String actNo;

    @Column( unique = true, nullable = false, length = 100)
    private  String actName;

    private BigDecimal transferLimit;

    // Account has a type
    @ManyToOne()
    private AccountType accountType;

    // @JoinTable(name = "user_accounts")
    @OneToMany(mappedBy = "account")
    private List<UserAccount> accountList;

    @OneToOne()
    private  Card card;


}
