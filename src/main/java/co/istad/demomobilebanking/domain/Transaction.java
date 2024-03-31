package co.istad.demomobilebanking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private  Account sender_account;

    @ManyToOne
    private  Account receiver_account;

    private BigDecimal amount;

    private String remark;

    private Boolean isPayment;

    private LocalDateTime transactionAt;

    private  Boolean isDelete;

}
