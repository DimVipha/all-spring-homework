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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true, nullable = false)
    private  String uuid;

    @Column(length = 50)
    private String name;

    @Column(length = 8)
    private String gender;

    @Column(unique = true)
    private  String oneSignalId;

    @Column(unique = true)
    private  String studentIdCard;

    private  String isStudent;
    private  Boolean isDeleted;


    @OneToMany(mappedBy = "user")
    List<UserAccount> userAccounts;


    @ManyToOne
    private  Transaction transaction;

    @OneToMany(mappedBy = "user")
    private List<UserRole>  userRoleList;


}