package co.istad.demomobilebanking.feature.user;

import co.istad.demomobilebanking.domain.User;
import co.istad.demomobilebanking.feature.user.DTO.UserCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNationalCardId(String nationalCardId);

    boolean existsByStudentIdCard(String studentIdCard);
    Boolean existsByUuid(String uuid);

    //using jpql:(positional parameter,binding parameter)

  /*  //positional parameter
//    @Query(value = "select *FROM  users WHERE uuid=?1 ",nativeQuery = true)

    // binding parameter
    @Query("SELECT u FROM User as u WHERE  u.uuid= :uuid")
    Optional <User> findByUuid(String  uuid);
*/

    //@Query(value = "SELECT * FROM users WHERE uuid = ?1", nativeQuery = true)
    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(String uuid);


    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

    @Modifying
    @Query("DELETE  User as u Where u.uuid=?1")
    void deleteAllByUuid(String uuid);

    // disable
    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = TRUE WHERE u.uuid = ?1")
    void updateByIsDeletedTrue(String uuid);

    // enable by uuid
    @Modifying
    @Query("UPDATE User AS u SET u.isDeleted = false WHERE u.uuid = ?1")
    void updateByIsDeletedFalse(String uuid);



}
