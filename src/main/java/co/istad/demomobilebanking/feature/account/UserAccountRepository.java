package co.istad.demomobilebanking.feature.account;

import co.istad.demomobilebanking.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
}
