package com.evandeemos.desafiopicpay.domain.transaction;

import com.evandeemos.desafiopicpay.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity(name = "Transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;
    private LocalDateTime timestamp;

    public Transaction(BigDecimal amount, User payer, User payee) {
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Recife"));
    }
}
