package co.istad.demomobilebanking.feature.user;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalCardId(String nationalCardId);

    boolean existsByStudentIdCard(String studentIdCard);

}
