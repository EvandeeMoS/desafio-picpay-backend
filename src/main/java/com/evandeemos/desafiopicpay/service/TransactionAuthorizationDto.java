package com.evandeemos.desafiopicpay.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionAuthorizationDto(
        String status,
        Data data
) {
    public record Data(
            boolean authorization
    ) {}
}
