package com.evandeemos.desafiopicpay.domain.user.dto;

import com.evandeemos.desafiopicpay.domain.user.User;
import com.evandeemos.desafiopicpay.domain.user.UserType;

public record UserDetailsDto(
        long id,
        String firstName,
        String lastName,
        String document,
        String email,
        UserType type
) {
    public UserDetailsDto(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getDocument(), user.getEmail(), user.getType());
    }
}
