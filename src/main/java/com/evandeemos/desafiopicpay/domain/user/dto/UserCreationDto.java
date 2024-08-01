package com.evandeemos.desafiopicpay.domain.user.dto;

import com.evandeemos.desafiopicpay.config.validation.UserCreationDtoGroupSequenceProvider;
import com.evandeemos.desafiopicpay.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@GroupSequenceProvider(UserCreationDtoGroupSequenceProvider.class)
public record UserCreationDto(
        @NotEmpty(message = "O campo firstName não pode estar vazio")
        String firstName,
        @NotEmpty(message = "O campo lastName não pode estar vazio")
        String lastName,
        @NotEmpty(message = "O campo document não pode estar vazio")
        @CPF(groups = org.hibernate.validator.constraints.br.CPF.class)
        @CNPJ(groups = org.hibernate.validator.constraints.br.CNPJ.class)
        String document,
        @NotEmpty(message = "O campo email não pode estar vazio")
        @Email(message = "Email inválido")
        String email,
        @NotEmpty(message = "O campo password não pode estar vazio")
        @Size(min = 8, message = "A senha deve conter ao menos 8 caracteres")
        String password,
        @NotNull
        UserType userType
) {
}
