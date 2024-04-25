package co.istad.demomobilebanking.feature.auth;

import co.istad.demomobilebanking.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
