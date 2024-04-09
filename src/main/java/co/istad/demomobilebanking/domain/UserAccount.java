package co.istad.demomobilebanking.domain;
import jakarta.persistence.*;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private  Account account;

    private  Boolean isDeleted;

    private LocalDateTime createAt;



}
