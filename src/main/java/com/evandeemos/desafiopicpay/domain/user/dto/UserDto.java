package com.evandeemos.desafiopicpay.domain.user.dto;

import com.evandeemos.desafiopicpay.domain.user.User;

public record UserDto(
        String firstName,
        String lastName,
        String email
) {
    public UserDto(User user) {
        this(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
