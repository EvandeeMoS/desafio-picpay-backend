package com.evandeemos.desafiopicpay.domain.wallet;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    BigDecimal money;

    public Wallet() {
        this.money = BigDecimal.valueOf(0);
    }
}
