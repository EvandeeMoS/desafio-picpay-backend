package com.evandeemos.desafiopicpay.domain.user;

public enum UserType {
    COMMON(org.hibernate.validator.constraints.br.CPF.class),
    MERCHANT(org.hibernate.validator.constraints.br.CNPJ.class);

    final Class<?> GROUP;

    UserType(Class<?> group) {
        this.GROUP = group;
    }

    public Class<?> getGROUP() {
        return GROUP;
    }
}
