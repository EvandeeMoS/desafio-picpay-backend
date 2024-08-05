package com.evandeemos.desafiopicpay.domain.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionCreationDto(
        @NotNull(message = "O valor da transação não pode ser nulo")
        @DecimalMin(value = "00.01", message = "O valor de pagamento deve ser no mínimo de 1 centavo")
        BigDecimal amount,
        @NotNull(message = "O id do pagador (payerId) deve ser específicado")
        long payerId,
        @NotNull(message = "O id do destinatário (payeeId) deve ser especificado")
        long payeeId
) {
}
