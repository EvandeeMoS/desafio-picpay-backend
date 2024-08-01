package com.evandeemos.desafiopicpay.domain.user.dto;

import com.evandeemos.desafiopicpay.domain.user.User;
import com.evandeemos.desafiopicpay.domain.user.UserType;

import java.time.LocalDateTime;
import java.time.ZoneId;

public record SuccefullyCreatedUserDto(
        long id,
        String firstName,
        String lastName,
        String document,
        String email,
        UserType type,
        LocalDateTime creationTime
) {
    public SuccefullyCreatedUserDto(User user) {
        this(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getEmail(),
                user.getType(),
                LocalDateTime.now(ZoneId.of("America/Recife"))
        );
    }
}
