package co.istad.demomobilebanking.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="users_accounts")
public class UserAccount {
    @Id
    private Long id;

    @ManyToOne()
    private User user;

    @ManyToOne
    private  Account account;

    private  Boolean isDeleted;

    private LocalDateTime createAt;



}
