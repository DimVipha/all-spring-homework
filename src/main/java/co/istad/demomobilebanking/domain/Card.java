package co.istad.demomobilebanking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String cvv;

    private String holder;
    private LocalDate issueAt;

    private LocalDate expireDate;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="type_id")
    private  CardType cardType;

    @OneToOne(mappedBy = "card")
    private Account account;
}
