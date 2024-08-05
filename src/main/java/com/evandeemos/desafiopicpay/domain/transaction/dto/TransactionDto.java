package com.evandeemos.desafiopicpay.domain.transaction.dto;

import com.evandeemos.desafiopicpay.domain.transaction.Transaction;

import java.math.BigDecimal;

public record TransactionDto(
        BigDecimal value,
        long payer,
        long payee
) {
    public TransactionDto(Transaction transaction) {
        this(transaction.getAmount(), transaction.getPayer().getId(), transaction.getPayee().getId());
    }
}
