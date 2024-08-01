package com.evandeemos.desafiopicpay.config.validation;

import com.evandeemos.desafiopicpay.domain.user.dto.UserCreationDto;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UserCreationDtoGroupSequenceProvider implements DefaultGroupSequenceProvider<UserCreationDto> {
    @Override
    public List<Class<?>> getValidationGroups(UserCreationDto userCreationDto) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(UserCreationDto.class);
        if (isUserTypeSelected(userCreationDto)) {
            groups.add(userCreationDto.userType().getGROUP());
        }
        return groups;
    }

    private boolean isUserTypeSelected(UserCreationDto userCreationDto) {
        return userCreationDto != null && userCreationDto.userType().getGROUP() != null;
    }
}
