package co.istad.demomobilebanking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="account_types")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(unique = true, nullable = false, length = 100)
    private  String name;

    @Column(columnDefinition = "TEXT")
    private  String description;

    private Boolean isDeleted;

//    @ManyToOne
//    private  AccountType accountType;

    @OneToMany(mappedBy = "accountType")
    private List<Account> Account;
}
