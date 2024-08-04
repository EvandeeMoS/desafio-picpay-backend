package com.evandeemos.desafiopicpay.domain.wallet.dto;

import com.evandeemos.desafiopicpay.domain.wallet.Wallet;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WalletUpdateDto(
        @NotNull
        long id,
        @NotNull
        @DecimalMin(value = "00.01")
        BigDecimal amount
) {
        public WalletUpdateDto(Wallet wallet) {
                this(wallet.getId(), wallet.getMoney());
        }
}
