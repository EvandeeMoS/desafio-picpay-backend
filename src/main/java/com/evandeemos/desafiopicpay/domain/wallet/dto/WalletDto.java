package com.evandeemos.desafiopicpay.domain.wallet.dto;

import com.evandeemos.desafiopicpay.domain.wallet.Wallet;

import java.math.BigDecimal;

public record WalletDto(
        long id,
        BigDecimal money
) {
    public WalletDto(Wallet wallet) {
        this(wallet.getId(), wallet.getMoney());
    }
}
