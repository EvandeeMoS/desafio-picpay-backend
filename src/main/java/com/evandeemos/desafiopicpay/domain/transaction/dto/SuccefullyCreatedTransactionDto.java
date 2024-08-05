package com.evandeemos.desafiopicpay.domain.transaction.dto;

import com.evandeemos.desafiopicpay.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SuccefullyCreatedTransactionDto(
        long id,
        BigDecimal amount,
        long payer,
        long payee,
        LocalDateTime timestamp
) {
    public SuccefullyCreatedTransactionDto(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getPayer().getId(),
                transaction.getPayee().getId(),
                transaction.getTimestamp());
    }
}
